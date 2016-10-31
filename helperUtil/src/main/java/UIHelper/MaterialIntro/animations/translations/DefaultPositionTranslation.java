package UIHelper.MaterialIntro.animations.translations;

import android.support.annotation.FloatRange;
import android.view.View;

import UIHelper.MaterialIntro.animations.IViewTranslation;

public class DefaultPositionTranslation implements IViewTranslation {
    @Override
    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
        view.setTranslationY(0);
    }
}
