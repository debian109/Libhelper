package namtran.helperutil.ActivityExample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import namtran.helperutil.BasicActivity.BaseActivity;
import namtran.helperutil.R;
import namtran.helperutil.Util.UtilsScrollAnimation;
import namtran.helperutil.mFragment.ScrollAnimationListViewFragment;
import namtran.helperutil.mFragment.ScrollAnimationRecyclerViewFragment;

/**
 * Created by NamTran on 4/3/2016.
 */
public class ScrollAnimationListviewAndRecyclerView extends BaseActivity implements View.OnClickListener{
    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.scrollanimationlistviewandrecyclerview_layout);
    }

    Button btnRecycleView,btnListview;
    public static Map<String, Integer> mEffectMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRecycleView = (Button) findViewById(R.id.btnRecycleView);
        btnListview = (Button) findViewById(R.id.btnListview);
        btnRecycleView.setOnClickListener(this);
        btnListview.setOnClickListener(this);
    }

    boolean check = false;
    boolean check1 = false;
    @Override
    public void onClick(View v) {
        int id = v.getId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.btnListview){
            fragmentTransaction.replace(R.id.framlay,new ScrollAnimationListViewFragment()).commit();
            check = true;
        }else if (id == R.id.btnRecycleView){
            fragmentTransaction.replace(R.id.framlay,new ScrollAnimationRecyclerViewFragment()).commit();
            check1 = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mEffectMap = UtilsScrollAnimation.buildEffectMap(this);
        UtilsScrollAnimation.populateEffectMenu(menu, new ArrayList<>(mEffectMap.keySet()), this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String strEffect = item.getTitle().toString();
        Toast.makeText(this, strEffect, Toast.LENGTH_SHORT).show();
        if (check){
        ScrollAnimationListViewFragment.setupJazzinessListView(ScrollAnimationListviewAndRecyclerView.mEffectMap.get(strEffect));
        }
        else if (check1){
        ScrollAnimationRecyclerViewFragment.setupJazzinessRecycle(ScrollAnimationListviewAndRecyclerView.mEffectMap.get(strEffect));}
        return true;
    }
}
