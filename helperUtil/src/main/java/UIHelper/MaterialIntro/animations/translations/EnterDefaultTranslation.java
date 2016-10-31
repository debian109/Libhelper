package UIHelper.MaterialIntro.animations.translations;

import android.support.annotation.FloatRange;
import android.view.View;

import UIHelper.MaterialIntro.animations.IViewTranslation;
import vn.namtran.basichelper.R;

public class EnterDefaultTranslation implements IViewTranslation {
    @Override
    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
        view.setTranslationY((1f - percentage) * view.getResources().getDimensionPixelOffset(R.dimen.material_intro_y_offset));
    }
}
