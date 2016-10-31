package UIHelper.MaterialIntro.listeners.clickListeners;

import android.view.View;

import UIHelper.MaterialIntro.BaseMaterialIntroActivity;
import UIHelper.MaterialIntro.animations.ViewTranslationWrapper;

public class PermissionNotGrantedClickListener implements View.OnClickListener {
    private final BaseMaterialIntroActivity activity;
    private final ViewTranslationWrapper translationWrapper;

    public PermissionNotGrantedClickListener(BaseMaterialIntroActivity activity, ViewTranslationWrapper translationWrapper) {
        this.activity = activity;
        this.translationWrapper = translationWrapper;
    }

    @Override
    public void onClick(View v) {
        translationWrapper.error();
        activity.showPermissionsNotGrantedError();
    }
}