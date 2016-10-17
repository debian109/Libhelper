package UIHelper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nam Tran on 10-Dec-15.
 */
public class UIHelper {

    /**
     * Hide soft keyboard
     * @param context
     * @param view
     */
    public static void hideSoftKeyboard (Context context,View view)
    {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     *  public final boolean requestFocus () : Call this to try to give focus to a specific view or to one of its descendants
     *  Returns :
     *      Whether this view or one of its descendants actually took focus.
     */

    /**
     * Show soft keyboard
     * @param context
     */
    public static void showSoftKeyboard (Context context)
    {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * show Keyboard Late int time
     *
     * @param lateTimer
     */
    public static void showKeyboardLate(final View view, int lateTimer) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(view, 0);
            }

        }, lateTimer);
    }

    /**
     * Set overflow scroll mode, used mainly shielding Meizu custom damping drop-down, to avoid conflict
     *
     * @param view
     * @param
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void disableOverScrollMode(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            view.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
    }

    /**
     * Front view for undetermined Aspect "estimate" width and height of the view, note that this method must be
     * use LayoutInflater.inflate (resid, container, false), and container! = Null, to be effective
     *
     * @param child
     */
    public static void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * Expand view when need but note shound not use with listview because when expand view usually put View in ScrollView
     * @param v
     */
    public static void expandView(final View v)
    {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int height = v.getMeasuredHeight();
        // hiện view lên
        v.setVisibility(View.VISIBLE);
        Animation animation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                // độ cao của View
                v.getLayoutParams().height = (int) (height * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        // 1dp/ms
        animation.setDuration((int) (height/(v.getContext().getResources().getDisplayMetrics().density *10)));
        v.startAnimation(animation);
    }

    /**
     * Narrowed View
     * @param v
     */
    public static void narrowedView(final View v)
    {
        final int height = v.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1)
                {
                    v.setVisibility(View.GONE);
                }
                else
                {
                    v.getLayoutParams().height = (int) (interpolatedTime - (height * interpolatedTime));
                    v.requestLayout();
                }
            }
            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration((long) (height/(v.getContext().getResources().getDisplayMetrics().density *15)));
        v.startAnimation(animation);
    }


    public static float convertDpToPixels(float dp,Context context){

        Resources resources = context.getResources();

        DisplayMetrics metrics = resources.getDisplayMetrics();

        float px = dp * (metrics.densityDpi/160f);
        return px;

    }

    public static float convertPixelsToDp(float px,Context context){

        Resources resources = context.getResources();

        DisplayMetrics metrics = resources.getDisplayMetrics();

        float dp = px / (metrics.densityDpi / 160f);

        return dp;
    }

    /**
     * Sets ListView height dynamically based on the height of the items.
     *
     * @param listView to be resized
     * @return true if the listView is successfully resized, false otherwise
     */
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}
