package namtran.helperutil.Pull_Zoom_Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import UIHelper.PullZoom.PullToZoomListViewEx;
import namtran.helperutil.R;

public class PullToZoomListActivity extends AppCompatActivity {

    private PullToZoomListViewEx listView;
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_list_view);

        listView = (PullToZoomListViewEx) findViewById(R.id.listview);
        tb = (Toolbar) findViewById(R.id.tb);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        String[] adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
                "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};

        listView.setAdapter(new ArrayAdapter<String>(PullToZoomListActivity.this, android.R.layout.simple_list_item_1, adapterData));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("zhuwenwu", "position = " + position);
                Toast.makeText(PullToZoomListActivity.this,"" + position,Toast.LENGTH_SHORT).show();
            }
        });

        listView.getHeaderView().findViewById(R.id.iv_user_head).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PullToZoomListActivity.this,"iv_user_head",Toast.LENGTH_SHORT).show();
            }
        });
        listView.getHeaderView().findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PullToZoomListActivity.this,"tv_register",Toast.LENGTH_SHORT).show();
            }
        });
        listView.getHeaderView().findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PullToZoomListActivity.this,"tv_login",Toast.LENGTH_SHORT).show();
            }
        });

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        listView.setHeaderLayoutParams(localObject);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_normal) {
            listView.setParallax(false);
            return true;
        } else if (id == R.id.action_parallax) {
            listView.setParallax(true);
            return true;
        } else if (id == R.id.action_show_head) {
            listView.setHideHeader(false);
            return true;
        } else if (id == R.id.action_hide_head) {
            listView.setHideHeader(true);
            return true;
        } else if (id == R.id.action_disable_zoom) {
            listView.setZoomEnabled(false);
            return true;
        } else if (id == R.id.action_enable_zoom) {
            listView.setZoomEnabled(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
