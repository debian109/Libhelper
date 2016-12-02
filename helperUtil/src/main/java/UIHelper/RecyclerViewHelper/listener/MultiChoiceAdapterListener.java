package UIHelper.RecyclerViewHelper.listener;

import android.view.View;

public interface MultiChoiceAdapterListener {

    void onSingleItemClickListener(View view, int position);

    void onSingleItemLongClickListener(View view, int position);

    void onUpdateItemListener(View view, int position);
}
