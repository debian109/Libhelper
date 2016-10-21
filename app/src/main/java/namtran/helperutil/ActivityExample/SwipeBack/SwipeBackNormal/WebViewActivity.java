package namtran.helperutil.ActivityExample.SwipeBack.SwipeBackNormal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import UIHelper.SwipeBackLayoutNormal.BaseSwipeBackActivityNormal;
import UIHelper.SwipeBackLayoutNormal.SwipeBackLayout;
import namtran.helperutil.R;

public class WebViewActivity extends BaseSwipeBackActivityNormal {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeback_normal_webview);
        setDragEdge(SwipeBackLayout.DragEdge.TOP);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_webview);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://github.com");
    }

}
