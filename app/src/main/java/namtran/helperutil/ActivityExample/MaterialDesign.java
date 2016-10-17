package namtran.helperutil.ActivityExample;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import namtran.helperutil.Adapter.SimpleRecyclerAdapter;
import namtran.helperutil.ActivityExample.MaterialActivity.AnimateToolbar;
import namtran.helperutil.ActivityExample.MaterialActivity.FabHideActivity;
import namtran.helperutil.ActivityExample.MaterialActivity.GmailStyleActivity;
import namtran.helperutil.ActivityExample.MaterialActivity.NavDrawerActivity;
import namtran.helperutil.ActivityExample.MaterialActivity.NestedToolbarActivity;
import namtran.helperutil.ActivityExample.MaterialActivity.PagerActivity;
import namtran.helperutil.ActivityExample.MaterialActivity.QuickReturnActivity;
import namtran.helperutil.ActivityExample.MaterialActivity.TabAnimationActivity;
import namtran.helperutil.ActivityExample.MaterialActivity.ToolbarOverlayActivity;
import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;
import namtran.helperutil.Util.Utils;

/**
 * Created by Nam Tran on 04-Apr-16.
 */
public class MaterialDesign extends BaseActivity {

    RecyclerView recyclerView;
    SimpleRecyclerAdapter adapter;
    RecyclerView mainRecyclerView;
    CardView listItem;
    Intent intent;

    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    boolean isUserFirstTime;

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.materialdesign_layout);
    }

    @Override
    protected String title() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        isUserFirstTime = Boolean.valueOf(Utils.readSharedSetting(MaterialDesign.this, PREF_USER_FIRST_TIME, "true"));

        mainRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        listItem = (CardView) findViewById(R.id.cardlist_item);
        recyclerView = (RecyclerView) findViewById(R.id.home_recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (adapter == null) {
            adapter = new SimpleRecyclerAdapter(this);
            recyclerView.setAdapter(adapter);
        }

        final Context context = this;

        adapter.SetOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        intent = new Intent(MaterialDesign.this, FabHideActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MaterialDesign.this, ToolbarOverlayActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MaterialDesign.this, NavDrawerActivity.class);
                        break;
                    case 3:
                        intent = new Intent(MaterialDesign.this, AnimateToolbar.class);
                        break;
                    case 4:
                        intent = new Intent(MaterialDesign.this, TabAnimationActivity.class);
                        break;
                    case 5:
                        intent = new Intent(MaterialDesign.this, NestedToolbarActivity.class);
                        break;
                    case 6:
                        intent = new Intent(MaterialDesign.this, QuickReturnActivity.class);
                        break;
                    case 7:
                        intent = new Intent(MaterialDesign.this, GmailStyleActivity.class);
                        break;
                    case 8:
                        intent = new Intent(MaterialDesign.this, PagerActivity.class);
                        break;

                    default:
                        Toast.makeText(getBaseContext(), "Undefined Click!", Toast.LENGTH_SHORT).show();
                }

                if (intent != null)
                    startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_navigator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
