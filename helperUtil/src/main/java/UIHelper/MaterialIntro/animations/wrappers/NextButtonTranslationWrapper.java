package UIHelper.MaterialIntro.animations.wrappers;

import android.view.View;

import UIHelper.MaterialIntro.animations.ViewTranslationWrapper;
import UIHelper.MaterialIntro.animations.translations.DefaultPositionTranslation;
import UIHelper.MaterialIntro.animations.translations.ExitDefaultTranslation;
import vn.namtran.basichelper.R;

public class NextButtonTranslationWrapper extends ViewTranslationWrapper {
    public NextButtonTranslationWrapper(View view) {
        super(view);

        setExitTranslation(new ExitDefaultTranslation())
                .setDefaultTranslation(new DefaultPositionTranslation())
                .setErrorAnimation(R.anim.material_intro_shake_it);
    }
}