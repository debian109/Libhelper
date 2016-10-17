package UIHelper.ScrollViewAuto;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;

import java.lang.reflect.Field;

/**
 * Auto scroll viewpager
 */
public class AutoScrollViewPager extends ViewPagerCompat
{
	
	private static final String TAG = "AutoScrollViewPager";
	
	/**
	 * The default switching cycle
	 */
	private static final int SCROLL_PERIOD_DEFAULT = 3 * 1000;
	
	/**
	 * The current switching cycle
	 */
	private long mScrollPeriod = SCROLL_PERIOD_DEFAULT;
	
	/**
	 * The default starting position
	 */
	private int mBeginIndex = 0;
	
	/**
	 * Can automatically play
	 */
	private boolean mAutoScrollable = false;
	
	/**
	 * Whether stopped
	 */
	private boolean mStopped = false;
	
	private AutoScroller mAutoScroller;
	
	private Handler mHandler = new Handler();
	
	private Runnable mScrollRunnable = new Runnable()
	{
		
		@Override
		public void run()
		{
			scrollSelf();
			
			mHandler.postDelayed(this, mScrollPeriod);
		}
	};
	
	/**
	 * 
	 * @param context
	 */
	public AutoScrollViewPager(Context context)
	{
		super(context);
		setUp();
	}
	
	/**
	 * 
	 * @param context
	 * @param attrs
	 */
	public AutoScrollViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setUp();
	}
	
	private void setUp()
	{
		// do nothing.
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		int action = MotionEventCompat.getActionMasked(ev);
		
		if (action == MotionEvent.ACTION_DOWN)
		{
			stopScroll();
		}
		else if (ev.getAction() == MotionEvent.ACTION_UP)
		{
			resumeScroll();
		}
		
		this.getParent().requestDisallowInterceptTouchEvent(true);
		
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		try
		{
			return super.onInterceptTouchEvent(ev);
		}
		catch (IllegalArgumentException e)
		{
			// Multi-touch exception
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Set default image background
	 * 
	 * @param resid
	 */
	public void setLoadingImage(int resid)
	{
		// TODO:
	}
	
	/**
	 * Start Scroll
	 * 
	 * @param duration
	 */
	public void startAutoScroll(int duration)
	{
		startAutoScroll(duration, 0);
	}
	
	/**
	 * Auto Scroll
	 * 
	 * @param duration
	 */
	public void startAutoScroll(int duration, int position)
	{
		this.mScrollPeriod = duration;
		
		if (this.getAdapter() instanceof AutoScrollPagerAdapter<?>)
		{
			this.mBeginIndex = position + 10000 * ((AutoScrollPagerAdapter<?>) this.getAdapter()).getCountReal();
		}
		else
		{
			this.mBeginIndex = position + 10000 * this.getAdapter().getCount();
		}
		
		this.mAutoScrollable = true;
		this.setCurrentItem(mBeginIndex);
		
		mHandler.removeCallbacks(mScrollRunnable);
		mHandler.postDelayed(mScrollRunnable, mScrollPeriod);
	}
	
	/**
	 * Resume Scroll
	 */
	public void resumeScroll()
	{
		if (mStopped)
		{
			mHandler.removeCallbacks(mScrollRunnable);
			mHandler.postDelayed(mScrollRunnable, mScrollPeriod);
		}
	}
	
	/**
	 * Change Scroll Period
	 * 
	 * @param duration
	 */
	public void changeScrollPeriod(int duration)
	{
		this.mScrollPeriod = duration;
	}
	
	/**
	 * Stop Scroll
	 */
	public void stopScroll()
	{
		mHandler.removeCallbacks(mScrollRunnable);
		mStopped = true;
	}
	
	/**
	 * Scroll itself
	 */
	protected void scrollSelf()
	{
		Field field = null;
		if (mAutoScroller == null)
		{
			mAutoScroller = new AutoScroller(getContext(), new AccelerateInterpolator());
		}
		
		try
		{
			field = ViewPagerCompat.class.getDeclaredField("mScroller");
			field.setAccessible(true);
			field.set(this, mAutoScroller);
			// Duration factor ++
			mAutoScroller.setFactor(AutoScroller.FACTOR_LONG);
		}
		catch (Exception e)
		{
			Log.e(TAG, "", e);
		}
		
		int newPosition = getCurrentItem() + 1;
		this.setCurrentItem(newPosition, true);
		
		try
		{
			field = ViewPagerCompat.class.getDeclaredField("mScroller");
			field.setAccessible(true);
			field.set(this, mAutoScroller);
			// Duration factor --
			mAutoScroller.setFactor(AutoScroller.FACTOR_SHORT);
		}
		catch (Exception e)
		{
			Log.e(TAG, "", e);
		}
	}
	
}
