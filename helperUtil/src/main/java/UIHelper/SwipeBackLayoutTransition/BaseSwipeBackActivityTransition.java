package UIHelper.SwipeBackLayoutTransition;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import vn.namtran.basichelper.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public abstract class BaseSwipeBackActivityTransition extends AppCompatActivity implements SwipeLayout.SwipeListener {

    private View mPreview;

    private float mInitOffset;

    private String mBitmapId;

    @Override
    public void setContentView(int layoutResID) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(MATCH_PARENT,MATCH_PARENT);
        SwipeLayout slideLayout = new SwipeLayout(BaseSwipeBackActivityTransition.this);
        FrameLayout contentView = new FrameLayout(this);
        mPreview = new View(this);
        slideLayout.setLayoutParams(layoutParams);
        contentView.setLayoutParams(layoutParams);
        mPreview.setLayoutParams(layoutParams);

        slideLayout.addView(mPreview);
        slideLayout.addView(contentView);

        super.setContentView(slideLayout);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        LayoutInflater inflater = LayoutInflater.from(this);
        mInitOffset = -(1.f / 3) * metrics.widthPixels;

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(MATCH_PARENT,
                MATCH_PARENT, Gravity.BOTTOM);
        final int marginTop = 0;
        lp.setMargins(0, marginTop, 0, 0);
        contentView.addView(inflater.inflate(layoutResID, null), lp);

        slideLayout.setShadowResource(R.drawable.sliding_back_shadow);
        slideLayout.setSlidingListener(this);
        slideLayout.setEdgeSize((int) (metrics.density * 20));

        mBitmapId = getIntent().getExtras().getString("bitmap_id");
        Bitmap bitmap = SwipeIntentUtils.getInstance().getBitmap(mBitmapId);
        if (null != bitmap) {
            if (Build.VERSION.SDK_INT >= 16) {
                mPreview.setBackground(new BitmapDrawable(bitmap));
            } else {
                mPreview.setBackgroundDrawable(new BitmapDrawable(bitmap));
            }

            SwipeIntentUtils.getInstance().setIsDisplayed(mBitmapId, true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeIntentUtils.getInstance().setIsDisplayed(mBitmapId, false);
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        if (slideOffset < 1) {
            mPreview.setTranslationX(mInitOffset * (1 - slideOffset));
        } else {
            mPreview.setTranslationX(0);
            finish();
            overridePendingTransition(0, 0);
        }
    }
}
