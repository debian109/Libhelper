package namtran.helperutil.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import LoadImage.ImageUtil;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 11-Dec-15.
 */
public class AdapterBasic extends ArrayAdapter<Integer> {
    Context mContext;
    int mLayout;
    Integer[] arrayAdapter;

    public AdapterBasic(Context context, int resource, Integer[] objects) {
        super(context, resource, objects);
        mContext = context;
        arrayAdapter = objects;
        mLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mLayout,null);
            imageView = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(imageView);
        }
        else
        {
            imageView = (ImageView) convertView.getTag();
        }

        ImageUtil.loadBitmapResource(mContext,arrayAdapter[position],imageView,400,400,null);

        return convertView;
    }
}
