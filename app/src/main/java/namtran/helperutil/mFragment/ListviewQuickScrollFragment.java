package namtran.helperutil.mFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import JavaHelper.Convert;
import UIHelper.ListQuickScroll;
import namtran.helperutil.Adapter.ListQuickScrollAdapter;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 02-Apr-16.
 */
public class ListviewQuickScrollFragment extends Fragment {
    private static final String FORMAT = "^[a-z,A-Z].*$";
    private ListView mListView;
    private ListQuickScroll mLetter;
    private String[] datas;
    private List<String> listCountries;
    // The first set of letters
    private List<String> mSections;
    // Storing data according to the first letter
    private Map<String, List<String>> mMap;
    // The first letter of the location set
    private List<Integer> mPositions;
    // A position corresponding to the first letter
    private Map<String, Integer> mIndexer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listviewquickscroll_fragment,container,false);
        mListView = (ListView) view.findViewById(R.id.mListView);
        mLetter = (ListQuickScroll) view.findViewById(R.id.friends_myletterlistview);
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
        mSections = new ArrayList<String>();
        mMap = new HashMap<String, List<String>>();
        mPositions = new ArrayList<Integer>();
        mIndexer = new HashMap<String, Integer>();

        for (int i = 0; i < listCountries.size(); i++) {
            String firstName = listCountries.get(i).substring(0, 1);
            if (firstName.matches(FORMAT)) {
                if (mSections.contains(firstName)) {
                    mMap.get(firstName).add(datas[i]);
                } else {
                    mSections.add(firstName);
                    List<String> list = new ArrayList<String>();
                    list.add(listCountries.get(i));
                    mMap.put(firstName, list);
                }
            } else {
                if (mSections.contains("#")) {
                    mMap.get("#").add(listCountries.get(i));
                } else {
                    mSections.add("#");
                    List<String> list = new ArrayList<String>();
                    list.add(listCountries.get(i));
                    mMap.put("#", list);
                }
            }
        }

        Collections.sort(mSections);
        int position = 0;
        for (int i = 0; i < mSections.size(); i++) {
            mIndexer.put(mSections.get(i), position);//Stored in the map, key, led by a string of letters, value headed letter position in the listview
            mPositions.add(position);// Initials listview position, stored in the list
            position += mMap.get(mSections.get(i)).size();// Calculate the next position in the first letter of the listview
        }
    }

    private void initView() {
        // TODO Auto-generated method stub
        ListQuickScrollAdapter adapter = new ListQuickScrollAdapter(getActivity(), listCountries, mSections, mPositions);
        mListView.setAdapter(adapter);
        mLetter.setOnItemClickListener(new ListQuickScroll.OnItemClickListener() {

            @Override
            public void onItemClick(String s) {
                if (mIndexer.get(s) != null) {
                    mListView.setSelection(mIndexer.get(s));
                }
            }
        });
    }
}
