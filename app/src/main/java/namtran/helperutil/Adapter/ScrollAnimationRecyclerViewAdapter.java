package namtran.helperutil.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import adapter.BaseRecyclerViewAdapter;
import namtran.helperutil.R;

/**
 * Created by NamTran on 4/3/2016.
 */
public class ScrollAnimationRecyclerViewAdapter extends BaseRecyclerViewAdapter<String,ScrollAnimationRecyclerViewAdapter.Myholder> {

    /**
     * @param context
     * @param list    the datas to attach the adapter
     */
    public ScrollAnimationRecyclerViewAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected void bindDataToItemView(Myholder myholder, String item) {
        myholder.setText(R.id.text,item);
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Myholder(inflateItemView(parent, R.layout.listquickscrollitem_layout));
    }

    public class Myholder extends BaseRecyclerViewAdapter.SparseArrayViewHolder{

        public Myholder(View itemView) {
            super(itemView);
        }
    }
}
