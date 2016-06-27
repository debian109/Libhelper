package UIHelper.ScrollViewAuto;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public interface IPagerAdapter
{
	
	public void destroyItem(ViewGroup container, int position, Object object);
	
	public int getCount();
	
	public Object instantiateItem(ViewGroup view, final int position);
	
	public boolean isViewFromObject(View view, Object object);
	
	public void restoreState(Parcelable state, ClassLoader loader);
	
	public Parcelable saveState();
	
}