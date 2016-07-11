package namtran.helperutil.ActivityExample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import UIHelper.SlidingUp.SlidingUpPanel;
import namtran.helperutil.R;

/**
 * Created by NamTran on 7/11/2016.
 */
public class SlidingUpActivity extends AppCompatActivity {

    private static final String TAG = "SlidingUpPanelTestActivity";

    private SlidingUpPanel mSlidingUpPanel;
    private ImageView mCoverDown;
    private TextView mCoverHint;
    private Button mClickToClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_up);

        mSlidingUpPanel = (SlidingUpPanel) findViewById(R.id.sliding_up_panel);
        mCoverDown = (ImageView) findViewById(R.id.cover_down);
        mCoverHint = (TextView) findViewById(R.id.cover_hint);
        mClickToClose = (Button) findViewById(R.id.click_to_close);

        mSlidingUpPanel.setOnPanelOpenListener(new SlidingUpPanel.OnPanelOpenListener() {
            @Override
            public void onPanelOpened() {
                showToast("Sliding up panel opened!");
            }
        });
        mSlidingUpPanel.setOnPanelCloseListener(new SlidingUpPanel.OnPanelCloseListener() {
            @Override
            public void onPanelClosed() {
                showToast("Sliding up panel closed!");
            }
        });
        mSlidingUpPanel.setOnPanelScrolledListener(new SlidingUpPanel.OnPanelScrollListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onPanelScrolled(float offset) {
                Log.d(TAG, "onPanelScrolled offset=" + offset);
                mCoverDown.setAlpha((int) ((1f - offset) * 255));
            }
        });

        mCoverDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Cover down pressed!");
            }
        });

        mClickToClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingUpPanel.closePanel();
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
