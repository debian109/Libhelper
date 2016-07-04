package namtran.helperutil.ActivityExample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import namtran.helperutil.BasicActivity.ImageSelectBaseActivity;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 13-Jan-16.
 */
public class ImageSelector extends ImageSelectBaseActivity {

    String URL = "https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s240-c/A%252520Photographer.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageselector_layout);
        Picasso.with(this).load(URL).into((ImageView) findViewById(R.id.ivImageSelected));
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //setImageSizeBoundary(400); // optional. default is 500.
                //setCropOption(1, 1);  // optional. default is no crop.
                //setCustomButtons(btnGallery, btnCamera, btnCancel); // you can set these buttons.
                startSelectImage();

            }
        });

        if (getSelectedImageFile().getPath()!=null)
        {
            Log.d("File",getSelectedImageFile().getPath());
        }

    }
}
