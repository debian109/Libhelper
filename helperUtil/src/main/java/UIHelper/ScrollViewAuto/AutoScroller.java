package UIHelper.ScrollViewAuto;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Auto Scroller
 */
public class AutoScroller extends Scroller
{
	
	/**
	 * {@link android.widget.Scroller#DEFAULT_DURATION}
	 */
	public static final int DEFAULT_DURATION = 250;
	
	/**
	 * Duration factor
	 */
	public static final int FACTOR_LONG = 6;
	/**
	 * Duration factor
	 */
	public static final int FACTOR_SHORT = 2;
	
	/**
	 * Duration
	 */
	private int mDurationX = DEFAULT_DURATION;
	
	/**
	 * Factor
	 */
	private double mFactor = 1;
	
	/**
	 * 
	 * @param context
	 */
	public AutoScroller(Context context)
	{
		super(context);
	}
	
	/**
	 * 
	 * @param context
	 * @param interpolator
	 */
	public AutoScroller(Context context, Interpolator interpolator)
	{
		super(context, interpolator);
	}
	
	@Override
	public void startScroll(int startX, int startY, int dx, int dy, int duration)
	{
		super.startScroll(startX, startY, dx, dy, mDurationX);
	}
	
	@Override
	public void startScroll(int startX, int startY, int dx, int dy)
	{
		super.startScroll(startX, startY, dx, dy, mDurationX);
	}
	
	/**
	 * When setting up long
	 * 
	 * @param duration
	 */
	public void setDurationX(int duration)
	{
		this.mDurationX = duration;
	}
	
	/**
	 * When setting up long
	 * 
	 * @return
	 */
	public int getDurationX()
	{
		return mDurationX;
	}
	
	/**
	 * When you set a growth factor, depending on the {@link com.opencdk.view.viewpager.AutoScroller#DEFAULT_DURATION}.
	 * 
	 * <pre>
	 * Duration = DEFAULT_DURATION * factor;
	 * 
	 * </pre>
	 * 
	 * @param factor
	 */
	public void setFactor(double factor)
	{
		this.mFactor = factor;
		this.mDurationX = (int) (DEFAULT_DURATION * factor);
	}
	
	/**
	 * Growth factor setting
	 * 
	 * @return
	 */
	public double getFactor()
	{
		return this.mFactor;
	}
	
}