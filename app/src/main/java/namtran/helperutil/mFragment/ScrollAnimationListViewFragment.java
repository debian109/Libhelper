package namtran.helperutil.mFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import JavaHelper.Convert;
import UIHelper.ListQuickScroll;
import UIHelper.ScrollAnimation.JazzyHelper;
import UIHelper.ScrollAnimation.JazzyListView;
import namtran.helperutil.Adapter.AdapterBasic2;
import namtran.helperutil.R;
import namtran.helperutil.ScrollAnimationListviewAndRecyclerView;
import namtran.helperutil.Util.UtilsScrollAnimation;

/**
 * Created by NamTran on 4/3/2016.
 */
public class ScrollAnimationListViewFragment extends Fragment {

    private static JazzyListView mList;
    static int mCurrentTransitionEffect = JazzyHelper.HELIX;
    private String[] datas;
    private List<String> listCountries;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scrollanimationlistview_fragment,container,false);
        mList = (JazzyListView) view.findViewById(R.id.list);
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
        mList.setAdapter(new AdapterBasic2(getActivity(), R.layout.scrollanimationitem, listCountries));
        setupJazzinessListView(mCurrentTransitionEffect);
    }
    public static void setupJazzinessListView(int effect) {
        mCurrentTransitionEffect = effect;
        mList.setTransitionEffect(mCurrentTransitionEffect);
    }
}
