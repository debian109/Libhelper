package namtran.helperutil.ActivityExample.RecyclerViewHelperExample;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import namtran.helperutil.Model.Movie;
import namtran.helperutil.R;

public class FragmentImageShow extends Fragment {
    private static final String TAG = FragmentImageShow.class.getSimpleName();

    private static final String MOVIE = "Movie";
    private static final String POSITIONRECEIVE = "FragmentImageShow_position_receive";
    private static final String POSITIONCURRENT = "FragmentImageShow_position_current";

    private ImageView image;
    private Movie movie;
    private int mPositionReceive;
    private int mPositionCurrent;

    private final Callback mImageCallback = new Callback() {
        @Override
        public void onSuccess() {
            startPostponedEnterTransition();
        }

        @Override
        public void onError() {
            startPostponedEnterTransition();
        }
    };

    public static FragmentImageShow newInstance(Movie movie,int positionReceive,int positionCurrent) {
        FragmentImageShow fragment = new FragmentImageShow();
        Bundle args = new Bundle();
        args.putParcelable(MOVIE,movie);
        args.putInt(POSITIONRECEIVE,positionReceive);
        args.putInt(POSITIONCURRENT,positionCurrent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            movie = bundle.getParcelable(MOVIE);
            mPositionReceive = bundle.getInt(POSITIONRECEIVE);
            mPositionCurrent = bundle.getInt(POSITIONCURRENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display_image, container, false);

        image = (ImageView) rootView.findViewById(R.id.image);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            image.setTransitionName(movie.getImage());
        }

        RequestCreator albumImageRequest = Picasso.with(getActivity()).load(movie.getImage()).placeholder(R.drawable.empty_photo);

        albumImageRequest.into(image, mImageCallback);
        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startPostponedEnterTransition() {
        if (mPositionCurrent == mPositionReceive) {
            image.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean onPreDraw() {
                    image.getViewTreeObserver().removeOnPreDrawListener(this);
                    getActivity().startPostponedEnterTransition();
                    return true;
                }
            });
        }
    }

    /**
     * Returns the shared element that should be transitioned back to the previous Activity,
     * or null if the view is not visible on the screen.
     */
    @Nullable
    ImageView getAlbumImage() {
        if (isViewInBounds(getActivity().getWindow().getDecorView(), image)) {
            return image;
        }
        return null;
    }

    /**
     * Returns true if {@param view} is contained within {@param container}'s bounds.
     */
    private static boolean isViewInBounds(@NonNull View container, @NonNull View view) {
        Rect containerBounds = new Rect();
        container.getHitRect(containerBounds);
        return view.getLocalVisibleRect(containerBounds);
    }
}