package namtran.helperutil.ActivityExample.RecyclerViewHelperExample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import namtran.helperutil.R;

public class DialogShowImage extends DialogFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {



    static DialogShowImage newInstance(ArrayList<DataMovie> dataMovies,int position) {
        DialogShowImage dialog = new DialogShowImage();
        dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog_NoTitle);
        Bundle bundle = new Bundle();
        bundle.putInt("Position",position);
        bundle.putParcelableArrayList("ListMovie",dataMovies);
        dialog.setArguments(bundle);
        return dialog;
    }

    private ViewPager viewPager;
    private ImageView btnBack;

    private List<DataMovie> dataMovies;
    private int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            dataMovies = bundle.getParcelableArrayList("ListMovie");
            position = bundle.getInt("Position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_display_image, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        btnBack = (ImageView) v.findViewById(R.id.backbutton);
        btnBack.setOnClickListener(this);

        MovieAdapterViewPager movieAdapterViewPager = new MovieAdapterViewPager(dataMovies);
        viewPager.setAdapter(movieAdapterViewPager);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(this);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (viewPager != null){
            viewPager.setCurrentItem(position);
            UpdateScreenListener listener = (UpdateScreenListener) getActivity();
            listener.onUpdate(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
