package namtran.helperutil.mFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapter.ItemTouchHelper.OnStartDragListener;
import adapter.ItemTouchHelper.SimpleItemTouchHelperCallback;
import adapter.listener.OnItemClickListener;
import adapter.listener.OnItemLongClickListener;
import namtran.helperutil.Adapter.TouchItemListVieAndGridViewAdapter;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 25-Mar-16.
 */
public class ListViewTouchItemFragment extends Fragment implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new RecyclerView(container.getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        TouchItemListVieAndGridViewAdapter adapter = new TouchItemListVieAndGridViewAdapter(getActivity(),list,this);

        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onClick(View view, String item) {
                Toast.makeText(getActivity(),"Click + " + item, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new OnItemLongClickListener<String>() {
            @Override
            public void onLongClick(View view, String item) {
                Toast.makeText(getActivity(),"Long Click + " + item, Toast.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    private void init(){
        list = new ArrayList<>();
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.dummy_items)));
    }
}
