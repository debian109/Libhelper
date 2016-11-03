package namtran.helperutil.ActivityExample.RecyclerViewExample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import UIHelper.RecyclerViewLib.Adapter.AdapterRecycler;
import UIHelper.RecyclerViewLib.Adapter.BaseAdapter;
import UIHelper.RecyclerViewLib.Adapter.SectionedAdapter;
import UIHelper.RecyclerViewLib.RecyclerViewHelper;
import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;

public class RecyclerViewActivity extends BaseActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, PullZoomHeaderFragment.newInstance())
                .commit();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PullZoomHeaderFragment extends PullZoomFragment {

        public static PullZoomHeaderFragment newInstance() {
            return new PullZoomHeaderFragment();
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2) {
                {
                    setSpanSizeLookup(new SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            return position == 0 ? 2 : 1;
                        }
                    });
                }
            });
        }

        @Override
        protected PullZoomAdapter createPullZoomAdapter(List<Integer> pullZoomData) {
            return new PullZoomHeaderAdapter(pullZoomData);
        }

        private class PullZoomHeaderAdapter extends PullZoomAdapter {
            public PullZoomHeaderAdapter() {
                super();
                addViewType(TYPE_HEADER, new ViewHolderFactory<PullZoomHeaderHolder>() {
                    @Override
                    public PullZoomHeaderHolder onCreateViewHolder(ViewGroup parent) {
                        return new PullZoomHeaderHolder(parent);
                    }
                });
            }

            public PullZoomHeaderAdapter(List<Integer> listData) {
                this();
                this.listData = listData;
            }

            @Override
            public Object getItem(int position) {
                if (position == 0) {
                    return ITEM_HEADER;
                }
                return listData.get(--position);
            }

            @Override
            public int getItemCount() {
                return listData.size() + 1;
            }

            private class PullZoomHeaderHolder extends AdapterRecycler.ViewHolder<Object> {
                private ImageView zoomView;
                private ViewGroup zoomHeaderContainer;

                public PullZoomHeaderHolder(@NonNull ViewGroup parent) {
                    this(LayoutInflater.from(getActivity()).inflate(R.layout.item_pull_zoom_header, parent, false));
                }

                public PullZoomHeaderHolder(@NonNull View view) {
                    super(view);
                    zoomView = (ImageView) view.findViewById(R.id.zoom_image_view);
                    zoomHeaderContainer = (ViewGroup) view.findViewById(R.id.zoom_header_container);
                }

                @Override
                public void bind(Object item, int position) {
                    mRecyclerView.setZoomView(zoomView);
                    mRecyclerView.setHeaderContainer(zoomHeaderContainer);
                }
            }
        }
    }

    public abstract static class PullZoomFragment extends Fragment {

        protected RecyclerViewHelper mRecyclerView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_pull_zoom_header, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mRecyclerView = (RecyclerViewHelper) view.findViewById(R.id.recycler_view);
            mRecyclerView.setAdapter(createPullZoomAdapter(createPullZoomData()));
        }

        private List<Integer> createPullZoomData() {
            List<Integer> pullZoomData = new ArrayList<>();
            Collections.addAll(pullZoomData,
                    new Integer[]{R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4,
                            R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8,
                            R.drawable.p9, R.drawable.p10});

            return pullZoomData;
        }

        protected abstract PullZoomAdapter createPullZoomAdapter(List<Integer> pullZoomData);

        public class PullZoomAdapter extends AdapterRecycler {

            protected List<Integer> listData;

            public PullZoomAdapter() {
                addViewType(Integer.class, new SectionedAdapter.ViewHolderFactory<PullZoomItemHolder>() {
                    @Override
                    public PullZoomItemHolder onCreateViewHolder(ViewGroup parent) {
                        return new PullZoomItemHolder(parent);
                    }
                });
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public int getItemCount() {
                return 0;
            }
        }

        private class PullZoomItemHolder extends SectionedAdapter.ViewHolder<Integer> {
            private ImageView imageView;
            private TextView textView;

            public PullZoomItemHolder(@NonNull ViewGroup parent) {
                this(LayoutInflater.from(getActivity()).inflate(R.layout.item_pull_zoom_item, parent, false));
            }

            public PullZoomItemHolder(@NonNull View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imageView);
                textView = (TextView) view.findViewById(R.id.text_view);
            }

            @Override
            public void bind(Integer item, int position) {
                imageView.setImageResource(item);
                textView.setText(getString(R.string.image_name, position));
            }
        }
    }
}
