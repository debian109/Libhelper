package namtran.helperutil.ActivityExample.RecyclerViewHelperExample;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import UIHelper.PullToZoom.PullZoomView;
import UIHelper.RecyclerViewHelper.adapter.SectionedRecyclerViewAdapter;
import UIHelper.RecyclerViewHelper.widget.MultiChoiceRecyclerView;
import UIHelper.RecyclerViewHelper.widget.MultiChoiceToolbar;
import UIHelper.RecyclerViewHelper.listener.OnSwipeTouchListener;
import namtran.helperutil.BaseActivity;
import namtran.helperutil.Model.DataMovie;
import namtran.helperutil.Model.Movie;
import namtran.helperutil.R;

public class RecyclerViewHelperActivity extends BaseActivity {

    public static final String LISTMOVIE = "ListMovie";
    public static final String POSITIONMOVIESEND = "position_sent";

    MultiChoiceRecyclerView recycler;
    PullZoomView pullZoomView;
    //    SectionedRecyclerViewAdapter1 adapter;
    MovieAdapterRecycler adapter;
    private int column = 3;
    private List<Integer> listFirstPostion = new ArrayList<>();
    private Bundle bundle;

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
        hideToolbar();
        pullZoomView = (PullZoomView) findViewById(R.id.pzv);
        recycler = (MultiChoiceRecyclerView) findViewById(R.id.recycler);

        pullZoomView.setIsParallax(true);
        pullZoomView.setIsZoomEnable(true);

        pullZoomView.setOnScrollListener(new PullZoomView.OnScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                System.out.println("onScroll   t:" + t + "  oldt:" + oldt);
            }

            @Override
            public void onHeaderScroll(int currentY, int maxY) {
                System.out.println("onHeaderScroll   currentY:" + currentY + "  maxY:" + maxY);
            }

            @Override
            public void onContentScroll(int l, int t, int oldl, int oldt) {
                System.out.println("onContentScroll   t:" + t + "  oldt:" + oldt);
            }
        });
        pullZoomView.setOnPullZoomListener(new PullZoomView.OnPullZoomListener() {
            @Override
            public void onPullZoom(int originHeight, int currentHeight) {
                System.out.println("onPullZoom  originHeight:" + originHeight + "  currentHeight:" + currentHeight);
            }

            @Override
            public void onZoomFinish() {
                System.out.println("onZoomFinish");
            }
        });

        final MyOnSwipeTouchListener swipeTouchListener = new MyOnSwipeTouchListener(this);

        setExitSharedElementCallback(new SharedElementCallback() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                if (bundle != null){
                    int PositionSend = bundle.getInt(ImageDisplayActivity.POSITIONRECEIVEIMAGE);
                    int PositionReceive = bundle.getInt(ImageDisplayActivity.POSITIONSENDIMAGE);
                    int sectionReceive = getSectionReturn(adapter.getListMovie(),PositionReceive);
                    int PositionReceiveReturn = getPositionInSection(adapter.getListMovie(),PositionReceive);
                    if (PositionSend != PositionReceiveReturn) {
                        // If startingPosition != currentPosition the user must have swiped to a
                        // different page in the DetailsActivity. We must update the shared element
                        // so that the correct one falls into place.
                        String newTransitionName = adapter.getListMovie().get(sectionReceive).getAllItemsInSection().get(PositionReceiveReturn).getImage();
                        View newSharedElement = recycler.findViewWithTag(newTransitionName);
                        if (newSharedElement != null) {
                            names.clear();
                            names.add(newTransitionName);
                            sharedElements.clear();
                            sharedElements.put(newTransitionName, newSharedElement);
                        }
                    }

                    bundle = null;
                }else {
                    // If mTmpReenterState is null, then the activity is exiting.
                    View navigationBar = findViewById(android.R.id.navigationBarBackground);
                    View statusBar = findViewById(android.R.id.statusBarBackground);
                    if (navigationBar != null) {
                        names.add(navigationBar.getTransitionName());
                        sharedElements.put(navigationBar.getTransitionName(), navigationBar);
                    }
                    if (statusBar != null) {
                        names.add(statusBar.getTransitionName());
                        sharedElements.put(statusBar.getTransitionName(), statusBar);
                    }
                }
            }
        });

        /*adapter = new SectionedRecyclerViewAdapter1(this);
        adapter.addSection(new ExpandableMovieSection(this,"Action",MovieAction()));
        adapter.addSection(new ExpandableMovieSection(this,"Cartoon",MovieCartoon()));
        adapter.addSection(new ExpandableMovieSection(this,"Horries",MovieHorries()));

        adapter.setSingleChooseListener(new SectionedRecyclerViewAdapter1.SingleChooseListener() {
            @Override
            public void SingleChoose(RecyclerView.ViewHolder holder, final int position) {
                Toast.makeText(RecyclerViewHelperActivity.this,"" + position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void SingleSwipeLeft(RecyclerView.ViewHolder holder) {
                if (column < 8){
                    column ++;
                    swipeLayout(recycler,column);
                }
            }

            @Override
            public void SingleSwipeRight(RecyclerView.ViewHolder holder) {
                if (column > 1){
                    column --;
                    swipeLayout(recycler,column);
                }
            }
        });

        adapter.setMultiChooseListener(new SectionedRecyclerViewAdapter1.MultiChooseListener() {
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
        });*/

        adapter = new MovieAdapterRecycler(this, getDataMovies());

        adapter.setMultiChooseListener(new MovieAdapterRecycler.MultiChooseListener() {
            @Override
            public void MultiChoose(View view, boolean state) {
                ImageView imageView = (ImageView) view.findViewById(R.id.tick_image);
                if (imageView != null) {
                    if (state)
                        imageView.setVisibility(View.VISIBLE);
                    else
                        imageView.setVisibility(View.INVISIBLE);
                }
            }
        });

        adapter.setTouchItemHolderListener(new MovieAdapterRecycler.TouchItemHolder() {

            @Override
            public void SingleChoose(RecyclerView.ViewHolder holder, int section, int position) {

                ImageView imageView = ((MovieHolder)holder).mImageView;

                Intent intent = new Intent(RecyclerViewHelperActivity.this, ImageDisplayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(POSITIONMOVIESEND, position - (section + 1));
                bundle.putParcelableArrayList(LISTMOVIE, (ArrayList<Movie>) listMovieRemoveHeader(adapter.getListMovie()));
                intent.putExtras(bundle);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(RecyclerViewHelperActivity.this,
                            imageView, imageView.getTransitionName()).toBundle());
                }else {
                startActivity(intent);
                }

            }

            @Override
            public void SingleSwipeLeft(RecyclerView.ViewHolder holder) {
                if (column < 8) {
                    column++;
                    swipeLayout(recycler, column);
                }
            }

            @Override
            public void SingleSwipeRight(RecyclerView.ViewHolder holder) {
                if (column > 1) {
                    column--;
                    swipeLayout(recycler, column);
                }
            }

            @Override
            public void SingleClickHeader(List<Movie> movieList) {
                if (movieList != null)
                    Toast.makeText(RecyclerViewHelperActivity.this, movieList.size() + "", Toast.LENGTH_SHORT).show();
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

        GridLayoutManager glm = new GridLayoutManager(this, column);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                /*switch(adapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter1.VIEW_TYPE_HEADER:
                        if (!listFirstPostion.contains(position + 1))
                            listFirstPostion.add(position + 1);
                        return column;
                    default:
                        if (listFirstPostion.contains(position))
                            if (column > 2)
                                return 1;
                            else
                                return 1;
                        else
                            return 1;
                }*/
                if (adapter.isHeader(position)) {
                    if (!listFirstPostion.contains(position + 1))
                        listFirstPostion.add(position + 1);
                    return column;
                } else {
                    if (listFirstPostion.contains(position))
                        if (column > 2)
                            return 1;
                        else
                            return 1;
                    else
                        return 1;
                }
            }
        });

        recycler.setLayoutManager(glm);
        recycler.setAdapter(adapter);
        //setHasFixedSize() is used to let the RecyclerView that its size will keep the same. This information will be used to optimize itself.
        recycler.setHasFixedSize(true);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setOnTouchListener(swipeTouchListener);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityReenter(int requestCode, Intent data) {
        super.onActivityReenter(requestCode, data);
        bundle = new Bundle(data.getExtras());
        int PositionSent = bundle.getInt(ImageDisplayActivity.POSITIONRECEIVEIMAGE);
        int PositionReceive = bundle.getInt(ImageDisplayActivity.POSITIONSENDIMAGE);
        int PositionReceiveWithSection = getPositionReturn(adapter.getListMovie(),PositionReceive);
        if (PositionSent != PositionReceiveWithSection) {
            recycler.scrollToPosition(PositionReceiveWithSection);
        }
        postponeEnterTransition();
        recycler.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recycler.getViewTreeObserver().removeOnPreDrawListener(this);
                // TODO: figure out why it is necessary to request layout here in order to get a smooth transition.
                recycler.requestLayout();
                startPostponedEnterTransition();
                return true;
            }
        });
    }

    private int getPositionReturn(List<DataMovie> dataMovies,int position){
        int pos = 0;
        int total = 0;
        for (int i = 0 ; i < dataMovies.size();i++){
            if ((total + dataMovies.get(i).getAllItemsInSection().size()) > position){
                pos = position + i;
                break;
            }else {
                total += dataMovies.get(i).getAllItemsInSection().size();
            }
        }
        return pos;
    }

    private int getPositionInSection(List<DataMovie> dataMovies,int position){
        int pos = 0;
        int total = 0;
        for (int i = 0 ; i < dataMovies.size();i++){
            if ((total + dataMovies.get(i).getAllItemsInSection().size()) > position){
                pos = position - total;
                break;
            }else {
                total += dataMovies.get(i).getAllItemsInSection().size();
            }
        }
        return pos;
    }

    private int getSectionReturn(List<DataMovie> dataMovies,int position){
        int sec = 0;
        int total = 0;
        for (int i = 0;i<dataMovies.size();i++){
            if ((total + dataMovies.get(i).getAllItemsInSection().size()) > position){
                sec = i;
                break;
            }else {
                total += dataMovies.get(i).getAllItemsInSection().size();
            }
        }
        return sec;
    }

    private List<Movie> listMovieRemoveHeader(List<DataMovie> dataMovies){
        List<Movie> movies = new ArrayList<>();
        for (int i = 0 ; i<dataMovies.size();i++){
            movies.addAll(dataMovies.get(i).getAllItemsInSection());
        }
        return movies;
    }

    private List<DataMovie> getDataMovies() {
        List<DataMovie> dataMovies = new ArrayList<>();
        dataMovies.add(new DataMovie("Action", MovieAction()));
        dataMovies.add(new DataMovie("Cartoon", MovieCartoon()));
        dataMovies.add(new DataMovie("Horries", MovieHorries()));
        return dataMovies;
    }

    private List<Movie> MovieAction() {
        List<Movie> movies = new ArrayList<>();
        for (Movie movie : Movie.getMovie()) {
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

        switch (item.getItemId()) {
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
                Toast.makeText(getApplicationContext(), "Always Single Click Mode [" + recycler.isInSingleClickMode() + "]", Toast.LENGTH_SHORT).show();
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
            if (column < 8) {
                column++;
                swipeLayout(recycler, column);
            }
        }

        @Override
        public void onSwipeRight() {
            if (column > 1) {
                column--;
                swipeLayout(recycler, column);
            }
        }
    }

    private void swipeLayout(RecyclerView recyclerView, int column) {

        /*final SectionedRecyclerViewAdapter1 adapter = (SectionedRecyclerViewAdapter1) recyclerView.getAdapter();
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        layoutManager.setSpanCount(column);

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyItemRangeChanged(0,adapter.getItemCount());
            }
        }, 100);*/

        final SectionedRecyclerViewAdapter adapter = (SectionedRecyclerViewAdapter) recyclerView.getAdapter();
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        layoutManager.setSpanCount(column);

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyItemRangeChanged(0, adapter.getItemCount());
            }
        }, 100);

    }
}
