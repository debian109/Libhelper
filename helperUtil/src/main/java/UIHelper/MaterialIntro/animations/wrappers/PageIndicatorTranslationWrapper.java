package UIHelper.MaterialIntro.animations.wrappers;

import android.view.View;

import UIHelper.MaterialIntro.animations.ViewTranslationWrapper;
import UIHelper.MaterialIntro.animations.translations.DefaultPositionTranslation;
import UIHelper.MaterialIntro.animations.translations.ExitDefaultTranslation;

public class PageIndicatorTranslationWrapper extends ViewTranslationWrapper {
    public PageIndicatorTranslationWrapper(View view) {
        super(view);

        setDefaultTranslation(new DefaultPositionTranslation())
                .setExitTranslation(new ExitDefaultTranslation());
    }
}