package namtran.helperutil;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import namtran.helperutil.BasicActivity.BaseActivity;
import namtran.helperutil.Pull_Zoom_Activity.PullToZoomListActivity;
import namtran.helperutil.Pull_Zoom_Activity.PullToZoomRecyclerActivity;
import namtran.helperutil.Pull_Zoom_Activity.PullToZoomScrollActivity;

/**
 * Created by nam.t on 28/06/2016.
 */
public class PullZoomActivity extends BaseActivity implements View.OnClickListener{

    Button btn_list,btn_scroll,btn_recycler_view;

    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.activity_pullzoom);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_scroll = (Button) findViewById(R.id.btn_scroll);
        btn_recycler_view = (Button) findViewById(R.id.btn_recycler_view);
        btn_list.setOnClickListener(this);
        btn_scroll.setOnClickListener(this);
        btn_recycler_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_list){
            startActivity(new Intent(PullZoomActivity.this, PullToZoomListActivity.class));
        }else if (id == R.id.btn_scroll){
            startActivity(new Intent(PullZoomActivity.this, PullToZoomScrollActivity.class));
        }else if (id == R.id.btn_recycler_view){
            startActivity(new Intent(PullZoomActivity.this, PullToZoomRecyclerActivity.class));
        }
    }
}
