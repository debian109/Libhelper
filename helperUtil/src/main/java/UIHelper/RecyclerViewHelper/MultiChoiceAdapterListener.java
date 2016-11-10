package UIHelper.RecyclerViewHelper;

import android.view.View;

interface MultiChoiceAdapterListener {

    void onSingleItemClickListener(View view, int position);

    void onSingleItemLongClickListener(View view, int position);

    void onUpdateItemListener(View view, int position);
}
