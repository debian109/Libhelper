package namtran.helperutil.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import namtran.helperutil.R;

/**
 * Created by Nam Tran on 11-Dec-15.
 */
public class AdapterBasic2 extends ArrayAdapter<String> {
    Context mContext;
    int mLayout;
    List<String> arrayAdapter;

    public AdapterBasic2(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        mContext = context;
        arrayAdapter = objects;
        mLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView imageView;
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mLayout,null);
            imageView = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(imageView);
        }
        else
        {
            imageView = (TextView) convertView.getTag();
        }

        imageView.setText(arrayAdapter.get(position));

        return convertView;
    }
}
