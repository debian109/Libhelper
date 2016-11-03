package UIHelper.RecyclerViewLib.Listener;

import android.view.View;

public interface AdapterListener {

    void onSingleItemClickListener(View view, int position);

    void onSingleItemLongClickListener(View view, int position);

    void onUpdateItemListener(View view, int position);
}
