package namtran.helperutil.ActivityExample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;

public class CardPageActivity extends BaseActivity {

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

    }


}
