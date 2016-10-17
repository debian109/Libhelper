package UIHelper.ScrollViewAuto;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public interface IPagerAdapter
{
	
	void destroyItem(ViewGroup container, int position, Object object);
	
	int getCount();
	
	Object instantiateItem(ViewGroup view, final int position);
	
	boolean isViewFromObject(View view, Object object);
	
	void restoreState(Parcelable state, ClassLoader loader);
	
	Parcelable saveState();
	
}