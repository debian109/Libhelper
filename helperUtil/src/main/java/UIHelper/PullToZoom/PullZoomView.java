package UIHelper.PullToZoom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.Scroller;

import vn.namtran.basichelper.R;

public class PullZoomView extends ScrollView {

    private static final String TAG_HEADER = "header";        //Head Layout Tag
    private static final String TAG_ZOOM = "zoom";            //Zoom the layout tag
    private static final String TAG_CONTENT = "content";      //Content Layout Tag

    private float sensitive = 1.5f;         //Amplification of the sensitivity coefficient
    private int zoomTime = 500;             //Head zoom time, in milliseconds
    private boolean isParallax = true;      //Whether the head has a parallax animation
    private boolean isZoomEnable = true;    //Whether to allow the head to enlarge

    private Scroller scroller;              //A secondary scaled object
    private boolean isActionDown = false;   //Whether the first received event is a Down event
    private boolean isZooming = false;      //Whether it is being scaled
    private MarginLayoutParams headerParams;//Head of the parameters
    private int headerHeight;               //The original height of the head
    private View headerView;                //Head layout
    private View zoomView;                  //View for zooming
    private View contentView;               //Main Content
    private float lastEventX;               //The X coordinate of the last occurrence of the Move event
    private float lastEventY;               //The Y coordinate of the last occurrence of the Move event
    private float downX;                    //The X coordinate of the Down event
    private float downY;                    //The Y coordinate of the Down event
    private int maxY;                       //Maximum allowable slip distance
    private int touchSlop;

    private OnScrollListener scrollListener;  //Scrolling the monitor

    public void setOnScrollListener(OnScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

        /**Scrolling listening, range from 0 ~ maxY*/
    public static abstract class OnScrollListener {
        public void onScroll(int l, int t, int oldl, int oldt) {
        }

        public void onHeaderScroll(int currentY, int maxY) {
        }

        public void onContentScroll(int l, int t, int oldl, int oldt) {
        }
    }

    private OnPullZoomListener pullZoomListener; //Pull down the amplified monitor

    public void setOnPullZoomListener(OnPullZoomListener pullZoomListener) {
        this.pullZoomListener = pullZoomListener;
    }

    public static abstract class OnPullZoomListener {
        public void onPullZoom(int originHeight, int currentHeight) {
        }

        public void onZoomFinish() {
        }
    }

    public PullZoomView(Context context) {
        this(context, null);
    }

    public PullZoomView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.scrollViewStyle);
    }

    public PullZoomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PullZoomView);
        sensitive = a.getFloat(R.styleable.PullZoomView_pzv_sensitive, sensitive);
        isParallax = a.getBoolean(R.styleable.PullZoomView_pzv_isParallax, isParallax);
        isZoomEnable = a.getBoolean(R.styleable.PullZoomView_pzv_isZoomEnable, isZoomEnable);
        zoomTime = a.getInt(R.styleable.PullZoomView_pzv_zoomTime, zoomTime);
        a.recycle();

        scroller = new Scroller(getContext());
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                maxY = contentView.getTop();//Only after the layout is complete can the correct values be obtained
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        findTagViews(this);
        if (headerView == null || zoomView == null || contentView == null) {
            throw new IllegalStateException("content, header, zoom are not allowed to be empty, set the tag in the Xml layout, or use the property settings");
        }
        headerParams = (MarginLayoutParams) headerView.getLayoutParams();
        headerHeight = headerParams.height;
        smoothScrollTo(0, 0);//If it is scrolled to the top, the default top is the top of the ListView
    }

    /**Recursively traverse all the View, query Tag */
    private void findTagViews(View v) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View childView = vg.getChildAt(i);
                String tag = (String) childView.getTag();
                if (tag != null) {
                    if (TAG_CONTENT.equals(tag) && contentView == null) contentView = childView;
                    if (TAG_HEADER.equals(tag) && headerView == null) headerView = childView;
                    if (TAG_ZOOM.equals(tag) && zoomView == null) zoomView = childView;
                }
                if (childView instanceof ViewGroup) {
                    findTagViews(childView);
                }
            }
        } else {
            String tag = (String) v.getTag();
            if (tag != null) {
                if (TAG_CONTENT.equals(tag) && contentView == null) contentView = v;
                if (TAG_HEADER.equals(tag) && headerView == null) headerView = v;
                if (TAG_ZOOM.equals(tag) && zoomView == null) zoomView = v;
            }
        }
    }

    private boolean scrollFlag = false;  //The mark is mainly to prevent the rapid sliding, onScroll callback may not get the maximum and minimum values

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollListener != null) scrollListener.onScroll(l, t, oldl, oldt);
        if (t >= 0 && t <= maxY) {
            scrollFlag = true;
            if (scrollListener != null) scrollListener.onHeaderScroll(t, maxY);
        } else if (scrollFlag) {
            scrollFlag = false;
            if (t < 0) t = 0;
            if (t > maxY) t = maxY;
            if (scrollListener != null) scrollListener.onHeaderScroll(t, maxY);
        }
        if (t >= maxY) {
            if (scrollListener != null) scrollListener.onContentScroll(l, t - maxY, oldl, oldt - maxY);
        }
        if (isParallax) {
            if (t >= 0 && t <= headerHeight) {
                headerView.scrollTo(0, -(int) (0.65 * t));
            } else {
                headerView.scrollTo(0, 0);
            }
        }
    }

    /**
     * Mainly used to solve RecyclerView nested, direct interception events, other problems may arise
     * If you do not need to use RecyclerView, you can comment out the code here
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = e.getX();
                downY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = e.getY();
                if (Math.abs(moveY - downY) > touchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isZoomEnable) return super.onTouchEvent(ev);

        float currentX = ev.getX();
        float currentY = ev.getY();
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = lastEventX = currentX;
                downY = lastEventY = currentY;
                scroller.abortAnimation();
                isActionDown = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isActionDown) {
                    downX = lastEventX = currentX;
                    downY = lastEventY = currentY;
                    scroller.abortAnimation();
                    isActionDown = true;
                }
                float shiftX = Math.abs(currentX - downX);
                float shiftY = Math.abs(currentY - downY);
                float dx = currentX - lastEventX;
                float dy = currentY - lastEventY;
                lastEventY = currentY;
                if (isTop()) {
                    if (shiftY > shiftX && shiftY > touchSlop) {
                        int height = (int) (headerParams.height + dy / sensitive + 0.5);
                        if (height <= headerHeight) {
                            height = headerHeight;
                            isZooming = false;
                        } else {
                            isZooming = true;
                        }
                        headerParams.height = height;
                        headerView.setLayoutParams(headerParams);
                        if (pullZoomListener != null) pullZoomListener.onPullZoom(headerHeight, headerParams.height);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isActionDown = false;
                if (isZooming) {
                    scroller.startScroll(0, headerParams.height, 0, -(headerParams.height - headerHeight), zoomTime);
                    isZooming = false;
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                break;
        }
        return isZooming || super.onTouchEvent(ev);
    }

    private boolean isStartScroll = false;          //Whether the current pull-down

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            isStartScroll = true;
            headerParams.height = scroller.getCurrY();
            headerView.setLayoutParams(headerParams);
            if (pullZoomListener != null) pullZoomListener.onPullZoom(headerHeight, headerParams.height);
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            if (pullZoomListener != null && isStartScroll) {
                isStartScroll = false;
                pullZoomListener.onZoomFinish();
            }
        }
    }

    private boolean isTop() {
        return getScrollY() <= 0;
    }

    public void setSensitive(float sensitive) {
        this.sensitive = sensitive;
    }

    public void setIsParallax(boolean isParallax) {
        this.isParallax = isParallax;
    }

    public void setIsZoomEnable(boolean isZoomEnable) {
        this.isZoomEnable = isZoomEnable;
    }

    public void setZoomTime(int zoomTime) {
        this.zoomTime = zoomTime;
    }
}