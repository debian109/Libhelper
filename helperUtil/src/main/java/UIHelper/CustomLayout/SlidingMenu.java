package UIHelper.CustomLayout;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import vn.namtran.basichelper.R;

public class SlidingMenu extends HorizontalScrollView {
	private int mScreenWidth;
	private int mMenuRightPadding;
	private int mMenuWidth;
	private int mHalfMenuWidth;

	private boolean isOpen;

	private boolean once;

	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mScreenWidth = ScreenUtils.getScreenWidth(context);

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyle, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			if (attr == R.styleable.SlidingMenu_rightPadding) {
				mMenuRightPadding = a.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50f,
								getResources().getDisplayMetrics()));
			}
		}
		a.recycle();
	}

	public SlidingMenu(Context context) {
		this(context, null, 0);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!once) {
			LinearLayout wrapper = (LinearLayout) getChildAt(0);
			ViewGroup menu = (ViewGroup) wrapper.getChildAt(1);
			ViewGroup content = (ViewGroup) wrapper.getChildAt(0);

			// mMenuWidth = mScreenWidth - mMenuRightPadding;
			mMenuWidth = mMenuRightPadding;
			mHalfMenuWidth = mMenuWidth / 2;
			menu.getLayoutParams().width = mMenuWidth;
			content.getLayoutParams().width = mScreenWidth;

		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			//
			this.scrollTo(0, 0);
			once = true;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		//
		case MotionEvent.ACTION_UP: {
			int scrollX = getScrollX();
			if (scrollX > mHalfMenuWidth) {
				this.smoothScrollTo(mMenuWidth, 0);
				isOpen = false;
			} else {
				this.smoothScrollTo(0, 0);
				isOpen = true;
			}
		}
			return true;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 *
	 */
	public void openMenu() {
		if (isOpen)
			return;
		this.smoothScrollTo(0, 0);
		isOpen = true;
	}

	/**
	 *
	 */
	public void closeMenu() {
		if (isOpen) {
			this.smoothScrollTo(mMenuWidth, 0);
			isOpen = false;
		}
	}

	/**
	 *
	 */
	public void toggle() {
		if (isOpen) {
			closeMenu();
		} else {
			openMenu();
		}
	}

	public static class ScreenUtils {
		private ScreenUtils() {
		/* cannot be instantiated */
			throw new UnsupportedOperationException("cannot be instantiated");
		}

		/**
		 * @param context
		 * @return
		 */
		public static int getScreenWidth(Context context) {
			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			DisplayMetrics outMetrics = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(outMetrics);
			return outMetrics.widthPixels;
		}

		/**
		 * @param context
		 * @return
		 */
		public static int getScreenHeight(Context context) {
			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			DisplayMetrics outMetrics = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(outMetrics);
			return outMetrics.heightPixels;
		}

		/**
		 * @param context
		 * @return
		 */
		public static int getStatusHeight(Context context) {

			int statusHeight = -1;
			try {
				Class<?> clazz = Class.forName("com.android.internal.R$dimen");
				Object object = clazz.newInstance();
				int height = Integer.parseInt(clazz.getField("status_bar_height")
						.get(object).toString());
				statusHeight = context.getResources().getDimensionPixelSize(height);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return statusHeight;
		}

		/**
		 * @param activity
		 * @return
		 */
		public static Bitmap snapShotWithStatusBar(Activity activity) {
			View view = activity.getWindow().getDecorView();
			view.setDrawingCacheEnabled(true);
			view.buildDrawingCache();
			Bitmap bmp = view.getDrawingCache();
			int width = getScreenWidth(activity);
			int height = getScreenHeight(activity);
			Bitmap bp = null;
			bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
			view.destroyDrawingCache();
			return bp;

		}

		/**
		 * @param activity
		 * @return
		 */
		public static Bitmap snapShotWithoutStatusBar(Activity activity) {
			View view = activity.getWindow().getDecorView();
			view.setDrawingCacheEnabled(true);
			view.buildDrawingCache();
			Bitmap bmp = view.getDrawingCache();
			Rect frame = new Rect();
			activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
			int statusBarHeight = frame.top;

			int width = getScreenWidth(activity);
			int height = getScreenHeight(activity);
			Bitmap bp = null;
			bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
					- statusBarHeight);
			view.destroyDrawingCache();
			return bp;

		}
	}
}
