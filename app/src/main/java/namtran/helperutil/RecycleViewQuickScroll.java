package namtran.helperutil;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import namtran.helperutil.BasicActivity.BaseActivity;
import namtran.helperutil.mFragment.ListviewQuickScrollFragment;
import namtran.helperutil.mFragment.RecycleViewQuickScrollFragment;

/**
 * Created by Nam Tran on 02-Apr-16.
 */
public class RecycleViewQuickScroll extends BaseActivity implements View.OnClickListener {
    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.recycleviewquickscroll_layout);
    }

    Button btnRecycleView,btnListview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRecycleView = (Button) findViewById(R.id.btnRecycleView);
        btnListview = (Button) findViewById(R.id.btnListview);
        btnRecycleView.setOnClickListener(this);
        btnListview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.btnRecycleView){
            fragmentTransaction.replace(R.id.framlay,new RecycleViewQuickScrollFragment()).commit();
        }else if (id == R.id.btnListview){
            fragmentTransaction.replace(R.id.framlay,new ListviewQuickScrollFragment()).commit();
        }
    }
}
