package UIHelper.MaterialIntro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import UIHelper.MaterialIntro.adapter.SlideFragment;
import vn.namtran.basichelper.R;

public class LastEmptySlideFragment extends SlideFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.material_intro_empty_fragment_slide, container, false);
    }

    @Override
    public int backgroundColor() {
        return R.color.material_intro_transparent;
    }

    @Override
    public int buttonsColor() {
        return R.color.material_intro_transparent;
    }
}