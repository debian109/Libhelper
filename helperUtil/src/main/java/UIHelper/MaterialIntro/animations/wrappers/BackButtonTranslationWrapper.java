package UIHelper.MaterialIntro.animations.wrappers;

import android.view.View;

import UIHelper.MaterialIntro.animations.ViewTranslationWrapper;
import UIHelper.MaterialIntro.animations.translations.DefaultPositionTranslation;
import UIHelper.MaterialIntro.animations.translations.EnterDefaultTranslation;
import UIHelper.MaterialIntro.animations.translations.ExitDefaultTranslation;

public class BackButtonTranslationWrapper extends ViewTranslationWrapper {
    public BackButtonTranslationWrapper(View view) {
        super(view);

        setEnterTranslation(new EnterDefaultTranslation())
                .setDefaultTranslation(new DefaultPositionTranslation())
                .setExitTranslation(new ExitDefaultTranslation());
    }
}