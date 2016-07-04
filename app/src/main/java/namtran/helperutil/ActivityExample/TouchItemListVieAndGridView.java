package namtran.helperutil.ActivityExample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import namtran.helperutil.BasicActivity.BaseActivity;
import namtran.helperutil.R;
import namtran.helperutil.mFragment.GridViewTouchItemFragment;
import namtran.helperutil.mFragment.ListViewTouchItemFragment;

/**
 * Created by Nam Tran on 25-Mar-16.
 */
public class TouchItemListVieAndGridView extends BaseActivity implements View.OnClickListener {
    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.touchitemlistviewandgridview_layout);
    }

    Button lvListView,gvGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lvListView = (Button) findViewById(R.id.lvListView);
        gvGridView = (Button) findViewById(R.id.gvGridView);
        lvListView.setOnClickListener(this);
        gvGridView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.lvListView){
            fragmentTransaction.replace(R.id.framlay,new ListViewTouchItemFragment()).commit();
        }else if (id == R.id.gvGridView){
            fragmentTransaction.replace(R.id.framlay,new GridViewTouchItemFragment()).commit();
        }
    }
}
