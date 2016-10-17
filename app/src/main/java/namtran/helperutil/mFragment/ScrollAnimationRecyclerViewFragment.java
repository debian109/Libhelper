package namtran.helperutil.mFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import JavaHelper.Convert;
import UIHelper.ScrollAnimation.JazzyHelper;
import UIHelper.ScrollAnimation.JazzyRecyclerViewScrollListener;
import namtran.helperutil.Adapter.ScrollAnimationRecyclerViewAdapter;
import namtran.helperutil.R;

/**
 * Created by NamTran on 4/3/2016.
 */
public class ScrollAnimationRecyclerViewFragment extends Fragment{

    private RecyclerView mList;
    static int mCurrentTransitionEffect = JazzyHelper.HELIX;
    private static JazzyRecyclerViewScrollListener jazzyScrollListener;
    private String[] datas;
    private List<String> listCountries;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scrollanimationrecyclerview_fragment,container,false);
        mList = (RecyclerView) view.findViewById(R.id.list);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    private void initData(){
        datas = getResources().getStringArray(R.array.countries);
        listCountries = Convert.convertArrayStringToList(datas);
    }
    private void initView(){
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList.setHasFixedSize(true);
        mList.setAdapter(new ScrollAnimationRecyclerViewAdapter(getActivity(), listCountries));
        jazzyScrollListener = new JazzyRecyclerViewScrollListener();
        mList.addOnScrollListener(jazzyScrollListener);
        setupJazzinessRecycle(mCurrentTransitionEffect);
    }

    public static void setupJazzinessRecycle(int effect) {
        mCurrentTransitionEffect = effect;
        jazzyScrollListener.setTransitionEffect(mCurrentTransitionEffect);
    }
}
