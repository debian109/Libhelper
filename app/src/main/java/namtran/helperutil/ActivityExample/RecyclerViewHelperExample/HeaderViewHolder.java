package namtran.helperutil.ActivityExample.RecyclerViewHelperExample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import namtran.helperutil.R;

class HeaderViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle;
    Button btnMore;

    public HeaderViewHolder(View view) {
        super(view);

        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        btnMore = (Button) view.findViewById(R.id.btnMore);
    }
}
