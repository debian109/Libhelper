package UIHelper.HideAndShowView;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by NashLegend on 2014/12/17 0017
 * 不要用于使用下拉拉出一个Header的下拉刷新样式
 */
public class AutoHideUtil {
    /**
     * AutoHideUtil.applyListViewAutoHide () is a helper class to quickly achieve MainActivity hidden inside the sliding method
     * It requires the user to provide the Header and Footer to hide in the method
     *
     * @param context
     * @param listView     Scrolling ListView
     * @param header       When you scroll to the top of the hidden element
     * @param footer       When you scroll to the bottom of the hidden element
     * @param headerHeight The height of the top element
     */
    public static void applyListViewAutoHide(Context context, ListView listView, View header, View footer, int headerHeight) {
        ListViewAutoHideTool tool = new ListViewAutoHideTool();
        tool.applyAutoHide(context, listView, header, footer, headerHeight);
    }

    /**
     * AutoHideUtil.applyListViewAutoHide () is a helper class to quickly achieve MainActivity hidden inside the sliding method
     * Does not require users to be hidden in the Header and Footer methods, specific methods to be implemented in a hidden user
     * callback
     *
     * @param context
     * @param listView    Scrolling ListView
     * @param headerHeight The height of the top element
     */
    public static void applyListViewAutoHide(Context context, ListView listView, int headerHeight, AutoHideListener autoHideListener) {
        ListViewAutoHideToolWithCallBack tool = new ListViewAutoHideToolWithCallBack();
        tool.applyAutoHide(context, listView, headerHeight, autoHideListener);
    }

    private static class ListViewAutoHideTool {

        View header;
        View footer;
        ListView listView;
        int touchSlop = 10;

        public ListViewAutoHideTool() {

        }

        public void applyAutoHide(Context context, ListView listView, View header, View footer, int headerHeight) {
            touchSlop = (int) (ViewConfiguration.get(context).getScaledTouchSlop() * 0.9);
            this.listView = listView;
            this.header = header;
            this.footer = footer;

            View mHeader = new View(listView.getContext());
            mHeader.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, headerHeight));
            mHeader.setBackgroundColor(Color.parseColor("#00000000"));
            listView.addHeaderView(mHeader);

            listView.setOnScrollListener(onScrollListener);
            listView.setOnTouchListener(onTouchListener);
        }


        AnimatorSet backAnimatorSet;

        private void animateBack() {
            if (hideAnimatorSet != null && hideAnimatorSet.isRunning()) {
                hideAnimatorSet.cancel();
            }
            if (backAnimatorSet != null && backAnimatorSet.isRunning()) {

            } else {
                backAnimatorSet = new AnimatorSet();
                ArrayList<Animator> animators = new ArrayList<>();
                if (header != null) {
                    ObjectAnimator headerAnimator = ObjectAnimator.ofFloat(header, "translationY", header.getTranslationY(), 0f);
                    animators.add(headerAnimator);
                }
                if (footer != null) {
                    ObjectAnimator footerAnimator = ObjectAnimator.ofFloat(footer, "translationY", footer.getTranslationY(), 0f);
                    animators.add(footerAnimator);
                }
                backAnimatorSet.setDuration(300);
                backAnimatorSet.playTogether(animators);
                backAnimatorSet.start();
            }
        }

        AnimatorSet hideAnimatorSet;

        private void animateHide() {
            if (backAnimatorSet != null && backAnimatorSet.isRunning()) {
                backAnimatorSet.cancel();
            }
            if (hideAnimatorSet != null && hideAnimatorSet.isRunning()) {

            } else {
                hideAnimatorSet = new AnimatorSet();
                ArrayList<Animator> animators = new ArrayList<>();
                if (header != null) {
                    ObjectAnimator headerAnimator = ObjectAnimator.ofFloat(header, "translationY", header.getTranslationY(), -header.getHeight());
                    animators.add(headerAnimator);
                }
                if (footer != null) {
                    ObjectAnimator footerAnimator = ObjectAnimator.ofFloat(footer, "translationY", footer.getTranslationY(), footer.getHeight());
                    animators.add(footerAnimator);
                }
                hideAnimatorSet.setDuration(300);
                hideAnimatorSet.playTogether(animators);
                hideAnimatorSet.start();
            }
        }

        View.OnTouchListener onTouchListener = new View.OnTouchListener() {

            float lastY = -1f;
            float currentY = -1f;
            int lastDirection = 0;
            int currentDirection = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastY = event.getY();
                        currentY = event.getY();
                        currentDirection = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (lastY < 0) {
                            lastY = event.getY();
                        }
                        if (listView.getFirstVisiblePosition() > 0) {
                            float tmpCurrentY = event.getY();
                            if (Math.abs(tmpCurrentY - lastY) > touchSlop) {
                                currentY = tmpCurrentY;
                                currentDirection = (int) (currentY - lastY);
                                if (lastDirection != currentDirection) {
                                    if (currentDirection < 0) {
                                        animateHide();
                                    } else {
                                        animateBack();
                                    }
                                }
                                lastY = currentY;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        currentDirection = 0;
                        currentY = -1f;
                        lastY = -1f;
                        break;
                }
                return false;
            }
        };

        AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
            int lastPosition = 0;
            int state = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                state = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    animateBack();
                }
                if (firstVisibleItem > 0) {
                    if (firstVisibleItem > lastPosition && state == SCROLL_STATE_FLING) {
                        animateHide();
                    }
                }
                lastPosition = firstVisibleItem;
            }
        };
    }

    private static class ListViewAutoHideToolWithCallBack {

        ListView listView;
        int touchSlop = 10;
        AutoHideListener autoHideListener;

        public ListViewAutoHideToolWithCallBack() {

        }

        public void applyAutoHide(Context context, ListView listView, int headerHeight, AutoHideListener autoHideListener) {
            touchSlop = (int) (ViewConfiguration.get(context).getScaledTouchSlop() * 0.9);
            this.listView = listView;
            this.autoHideListener = autoHideListener;

            View header = new View(listView.getContext());
            header.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, headerHeight));
            header.setBackgroundColor(Color.parseColor("#00000000"));
            listView.addHeaderView(header);

            listView.setOnScrollListener(onScrollListener);
            listView.setOnTouchListener(onTouchListener);
        }

        private void animateBack() {
            if (autoHideListener != null) {
                autoHideListener.animateBack();
            }
        }

        private void animateHide() {
            if (autoHideListener != null) {
                autoHideListener.animateHide();
            }
        }

        View.OnTouchListener onTouchListener = new View.OnTouchListener() {

            float lastY = -1f;
            float currentY = -1f;
            int lastDirection = 0;
            int currentDirection = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastY = event.getY();
                        currentY = event.getY();
                        currentDirection = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (lastY < 0) {
                            lastY = event.getY();
                        }
                        if (listView.getFirstVisiblePosition() > 0) {
                            float tmpCurrentY = event.getY();
                            if (Math.abs(tmpCurrentY - lastY) > touchSlop) {
                                currentY = tmpCurrentY;
                                currentDirection = (int) (currentY - lastY);
                                if (lastDirection != currentDirection) {
                                    if (currentDirection < 0) {
                                        animateHide();
                                    } else {
                                        animateBack();
                                    }
                                }
                                lastY = currentY;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        currentDirection = 0;
                        currentY = -1f;
                        lastY = -1f;
                        break;
                }
                return false;
            }
        };

        AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
            int lastPosition = 0;
            int state = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                state = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    animateBack();
                }
                if (firstVisibleItem > 0) {
                    if (firstVisibleItem > lastPosition && state == SCROLL_STATE_FLING) {
                        animateHide();
                    }
                }
                lastPosition = firstVisibleItem;
            }
        };
    }
}
