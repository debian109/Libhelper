package namtran.helperutil.ActivityExample.WoWoViewPagerExample;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;

public class WoWoViewPagerActivityExample extends BaseActivity
        implements View.OnClickListener {

    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.activity_wowo_viewpager_main);
    }

    @Override
    protected String title() {
        return getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.wowo_translation_animation).setOnClickListener(this);
        findViewById(R.id.wowo_scale_animation).setOnClickListener(this);
        findViewById(R.id.wowo_alpha_animation).setOnClickListener(this);
        findViewById(R.id.wowo_drawable_color_animation).setOnClickListener(this);
        findViewById(R.id.wowo_textview_color_animation).setOnClickListener(this);
        findViewById(R.id.wowo_background_color_animation).setOnClickListener(this);
        findViewById(R.id.wowo_layer_list_color_animation).setOnClickListener(this);
        findViewById(R.id.wowo_state_list_color_animation).setOnClickListener(this);
        findViewById(R.id.wowo_rotation_animation).setOnClickListener(this);
        findViewById(R.id.wowo_path_animation).setOnClickListener(this);
        findViewById(R.id.app_intro_example).setOnClickListener(this);
        findViewById(R.id.cv_example).setOnClickListener(this);
        findViewById(R.id.wowo_textview_textsize_animation).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SetEaseTypeActivity.class);
        switch (v.getId()) {
            case R.id.wowo_translation_animation:
                intent.putExtra("AnimationType", "WoWoTranslationAnimation");
                break;
            case R.id.wowo_scale_animation:
                intent.putExtra("AnimationType", "WoWoScaleAnimation");
                break;
            case R.id.wowo_alpha_animation:
                intent.putExtra("AnimationType", "WoWoAlphaAnimation");
                break;
            case R.id.wowo_drawable_color_animation:
                intent.putExtra("AnimationType", "WoWoShapeColorAnimation");
                break;
            case R.id.wowo_textview_color_animation:
                intent.putExtra("AnimationType", "WoWoTextViewColorAnimation");
                break;
            case R.id.wowo_background_color_animation:
                intent.putExtra("AnimationType", "WoWoBackgroundColorAnimation");
                break;
            case R.id.wowo_layer_list_color_animation:
                intent.putExtra("AnimationType", "WoWoLayerListColorAnimation");
                break;
            case R.id.wowo_state_list_color_animation:
                intent.putExtra("AnimationType", "WoWoStateListColorAnimation");
                break;
            case R.id.wowo_rotation_animation:
                intent.putExtra("AnimationType", "WoWoRotationAnimation");
                break;
            case R.id.wowo_path_animation:
                intent.putExtra("AnimationType", "WoWoPathAnimation");
                break;
            case R.id.wowo_textview_textsize_animation:
                intent.putExtra("AnimationType", "WoWoTextViewTextSizeAnimation");
                break;
            case R.id.app_intro_example:
                startActivity(new Intent(this, AppIntroExampleActivity.class));
                return;
            case R.id.cv_example:
                startActivity(new Intent(this, CVExampleActivity.class));
                return;
        }
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.wowo_viewpager_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_github:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://github.com/Nightonke/WoWoViewPager")));
                return true;
            case R.id.action_developer:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://github.com/Nightonke")));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
