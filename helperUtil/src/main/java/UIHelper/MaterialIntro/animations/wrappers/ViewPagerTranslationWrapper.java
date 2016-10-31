package UIHelper.MaterialIntro.animations.wrappers;

import android.view.View;

import UIHelper.MaterialIntro.animations.ViewTranslationWrapper;
import UIHelper.MaterialIntro.animations.translations.AlphaTranslation;
import UIHelper.MaterialIntro.animations.translations.DefaultAlphaTranslation;

public class ViewPagerTranslationWrapper extends ViewTranslationWrapper {
    public ViewPagerTranslationWrapper(View view) {
        super(view);

        setDefaultTranslation(new DefaultAlphaTranslation())
                .setExitTranslation(new AlphaTranslation());
    }
}