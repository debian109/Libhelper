package UIHelper.MaterialMenu;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import vn.namtran.basichelper.R;

public class MaterialMenuIconCompat extends MaterialMenuBase {

    public MaterialMenuIconCompat(AppCompatActivity activity, int color, MaterialMenuDrawable.Stroke stroke) {
        super(activity, color, stroke);
    }

    public MaterialMenuIconCompat(AppCompatActivity activity, int color, MaterialMenuDrawable.Stroke stroke, int transformDuration) {
        super(activity, color, stroke, transformDuration);
    }

    @Override
    protected View getActionBarHomeView(Activity activity) {
        Resources resources = activity.getResources();
        return activity.getWindow().getDecorView().findViewById(
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
                        ? resources.getIdentifier("android:id/home", null, null)
                        : R.id.home
        );
    }

    @Override
    protected View getActionBarUpView(Activity activity) {
        Resources resources = activity.getResources();
        View decorView = activity.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ViewGroup actionBarView = (ViewGroup) activity.getWindow().getDecorView().findViewById(
                    resources.getIdentifier("android:id/action_bar", null, null)
            );
            View homeView = actionBarView.getChildAt(
                    actionBarView.getChildCount() > 1 ? 1 : 0
            );
            return homeView.findViewById(
                    resources.getIdentifier("android:id/up", null, null)
            );
        } else {
            // there are duplicate up ids; extract it from non expanded action bar view
            ViewGroup actionBarView = (ViewGroup) decorView.findViewById(R.id.action_bar);
            View homeView = actionBarView.getChildAt(1);
            return homeView.findViewById(R.id.up);
        }
    }

    @Override
    protected boolean providesActionBar() {
        return true;
    }

    @Override
    protected void setActionBarSettings(Activity activity) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable());
    }
}