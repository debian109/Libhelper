package namtran.helperutil.ActivityExample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import UIHelper.RecyclerViewHelper.MultiChoiceRecyclerView;
import UIHelper.RecyclerViewHelper.MultiChoiceToolbar;
import UIHelper.RecyclerViewHelper.OnSwipeTouchListener;
import UIHelper.RecyclerViewHelper.SectionedRecyclerViewAdapter;
import namtran.helperutil.Adapter.ExpandableMovieSection;
import namtran.helperutil.BaseActivity;
import namtran.helperutil.Model.Movie;
import namtran.helperutil.R;

public class RecyclerViewHelperActivity extends BaseActivity {

    MultiChoiceRecyclerView recycler;
    SectionedRecyclerViewAdapter adapter;
    private int column = 3;
    private List<Integer> listFirstPostion = new ArrayList<>();
    GridLayoutManager glm;

    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.activity_recyclerview_helper_main);
    }

    @Override
    protected String title() {
        return getClass().getSimpleName();
    }

    @Override
    protected boolean isShowBackButton() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recycler = (MultiChoiceRecyclerView) findViewById(R.id.recycler);

        adapter = new SectionedRecyclerViewAdapter(this);
        adapter.addSection(new ExpandableMovieSection(this,"Action",MovieAction()));
        adapter.addSection(new ExpandableMovieSection(this,"Cartoon",MovieCartoon()));
        adapter.addSection(new ExpandableMovieSection(this,"Horries",MovieHorries()));

        final MyOnSwipeTouchListener swipeTouchListener = new MyOnSwipeTouchListener(this);

        adapter.setSingleChooseListener(new SectionedRecyclerViewAdapter.SingleChooseListener() {
            @Override
            public void SingleChoose(RecyclerView.ViewHolder holder, final int position) {
                Toast.makeText(RecyclerViewHelperActivity.this,"" + position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void SingleSwipeLeft() {
                Toast.makeText(RecyclerViewHelperActivity.this,"Left",Toast.LENGTH_SHORT).show();
                if (column < 8)
                    column ++;
                glm.setSpanCount(column);
                recycler.setLayoutManager(glm);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void SingleSwipeRight() {
                Toast.makeText(RecyclerViewHelperActivity.this,"Right",Toast.LENGTH_SHORT).show();
                if (column > 1)
                    column --;
                glm.setSpanCount(column);
                recycler.setLayoutManager(glm);
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setMultiChooseListener(new SectionedRecyclerViewAdapter.MultiChooseListener() {
            @Override
            public void MultiChoose(View view, boolean state) {
                ImageView imageView = (ImageView) view.findViewById(R.id.tick_image);
                if (imageView != null){
                    if (state)
                        imageView.setVisibility(View.VISIBLE);
                    else
                        imageView.setVisibility(View.INVISIBLE);
                }
            }
        });

        MultiChoiceToolbar multiChoiceToolbar = new MultiChoiceToolbar.Builder(this, getToolBar())
                .setDefaultToolbarTitle("Toolbar + Controls")
                .setSelectedToolbarTitle("item selected")
                .setMultiPrimaryColor(R.color.colorPrimaryMulti)
                .setMultiPrimaryColorDark(R.color.colorPrimaryDarkMulti)
                .setIcon(R.drawable.btn_back, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                })
                .build();

        recycler.setHasFixedSize(true);

        recycler.setMultiChoiceToolbar(multiChoiceToolbar);

        glm = new GridLayoutManager(this, column);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                switch(adapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        if (!listFirstPostion.contains(position + 1))
                            listFirstPostion.add(position + 1);
                        return column;
                    default:
                        if (listFirstPostion.contains(position))
                            if (column > 1)
                                return column - 1;
                            else
                                return 1;
                        else
                            return 1;
                }
            }
        });

        recycler.setLayoutManager(glm);
        recycler.setAdapter(adapter);
        recycler.setOnTouchListener(swipeTouchListener);

    }

    private List<Movie> MovieAction(){
        List<Movie> movies = new ArrayList<>();
        for (Movie movie : Movie.getMovie()){
            if (movie.getType().equals("Action"))
                movies.add(movie);
        }
        return movies;
    }

    private List<Movie> MovieCartoon() {
        List<Movie> movies = new ArrayList<>();
        for (Movie movie : Movie.getMovie()) {
            if (movie.getType().equals("Cartoon"))
                movies.add(movie);
        }
        return movies;
    }

    private List<Movie> MovieHorries() {
        List<Movie> movies = new ArrayList<>();
        for (Movie movie : Movie.getMovie()) {
            if (movie.getType().equals("Horries"))
                movies.add(movie);
        }
        return movies;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.select_all:
                recycler.selectAll();
                return true;
            case R.id.deselect_all:
                recycler.deselectAll();
                return true;
            case R.id.select_3:
                recycler.select(2);
                return true;
            case R.id.single_click_mode:
                recycler.setSingleClickMode(!recycler.isInSingleClickMode());
                Toast.makeText(getApplicationContext(), "Always Single Click Mode ["+recycler.isInSingleClickMode()+"]", Toast.LENGTH_SHORT).show();
                return true;
        }

        return false;
    }

    private class MyOnSwipeTouchListener extends OnSwipeTouchListener {
        public MyOnSwipeTouchListener(Context c) {
            super(c);
        }

        @Override
        public void onSwipeLeft() {
            Toast.makeText(RecyclerViewHelperActivity.this,"Left",Toast.LENGTH_SHORT).show();
            if (column < 8)
                column ++;
            glm.setSpanCount(column);
            recycler.setLayoutManager(glm);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onSwipeRight() {
            Toast.makeText(RecyclerViewHelperActivity.this,"Right",Toast.LENGTH_SHORT).show();
            if (column > 1)
                column --;
            glm.setSpanCount(column);
            recycler.setLayoutManager(glm);
            adapter.notifyDataSetChanged();
        }
    };
}
