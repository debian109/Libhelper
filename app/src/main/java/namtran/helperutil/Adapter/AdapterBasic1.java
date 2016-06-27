package namtran.helperutil.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import namtran.helperutil.R;

/**
 * Created by Nam Tran on 11-Dec-15.
 */
public class AdapterBasic1 extends ArrayAdapter<File> {
    Context mContext;
    int mLayout;
    List<File> arrayAdapter;

    public AdapterBasic1(Context context, int resource, List<File> objects) {
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

        /*HelperImage.loadBitmapFile(mContext, arrayAdapter.get(position), imageView, 400, 400);*/

        /*ImageLoader imageLoader = new ImageLoader(mContext);
        imageLoader.DisplayImage(arrayAdapter.get(position),imageView);*/

        Picasso.with(mContext).load(arrayAdapter.get(position)).error(R.drawable.argentina).placeholder(R.drawable.empty_photo).into(imageView);
        return convertView;
    }
}
