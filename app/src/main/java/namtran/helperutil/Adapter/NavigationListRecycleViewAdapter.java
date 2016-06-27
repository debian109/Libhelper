package namtran.helperutil.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import UIHelper.TextDrawableCycle.ColorGenerator;
import UIHelper.TextDrawableCycle.TextDrawable;
import adapter.BaseRecyclerViewAdapter;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 30-Mar-16.
 */
public class NavigationListRecycleViewAdapter extends BaseRecyclerViewAdapter<String,NavigationListRecycleViewAdapter.MyHolder>{

    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;

    /**
     * @param context
     * @param list    the datas to attach the adapter
     */
    public NavigationListRecycleViewAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected void bindDataToItemView(MyHolder myHolder, String item) {
        TextDrawable textDrawable = TextDrawable.builder()
                .buildRoundRect(String.valueOf(item.charAt(0)), mColorGenerator.getColor(item.charAt(0)),90);
        myHolder.setText(R.id.text, item);
        myHolder.setImageDrawable(R.id.imgAvatar,textDrawable);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflateItemView(parent, R.layout.navigationcardviewitem_layout));
    }

    public class MyHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder{

        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
