package namtran.helperutil.ActivityExample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;

public class CardPageActivity extends BaseActivity {

    private ImageView cardpage_img_background;
    private ViewPager cardview_vp;
    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.activity_cardpage_main);
    }

    @Override
    protected String title() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardpage_img_background = (ImageView) findViewById(R.id.cardpage_img_background);
        cardview_vp = (ViewPager) findViewById(R.id.cardview_vp);
    }
}
