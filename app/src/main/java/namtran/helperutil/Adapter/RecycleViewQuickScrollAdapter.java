package namtran.helperutil.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import adapter.BaseRecyclerViewAdapter;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 02-Apr-16.
 */
public class RecycleViewQuickScrollAdapter extends BaseRecyclerViewAdapter<String,RecycleViewQuickScrollAdapter.Myholder>{

    /**
     * @param context
     * @param list    the datas to attach the adapter
     */
    public RecycleViewQuickScrollAdapter(Context context, List<String> list) {
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
