package namtran.helperutil.mFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import UIHelper.FlowingDrawer.CircleTransformation;
import UIHelper.FlowingDrawer.MenuFragment;
import namtran.helperutil.R;


public class FlowingDrawerMyMenuFragment extends MenuFragment {

    private ImageView ivMenuUserProfilePhoto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flowing_drawer_fragment_menu, container,
                false);
        ivMenuUserProfilePhoto = (ImageView) view.findViewById(R.id.ivMenuUserProfilePhoto);
        return  setupReveal(view) ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupHeader();
    }

    private void setupHeader() {
        int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
        String profilePhoto = getResources().getString(R.string.user_profile_photo);
        try {
            Picasso.with(getActivity())
                    .load(profilePhoto)
                    .placeholder(R.drawable.img_circle_placeholder)
                    .resize(avatarSize, avatarSize)
                    .centerCrop()
                    .transform(new CircleTransformation())
                    .into(ivMenuUserProfilePhoto);
        }catch (IllegalArgumentException e){

        }

    }

    public void onOpenMenu(){
        Toast.makeText(getActivity(),"onOpenMenu", Toast.LENGTH_SHORT).show();
    }
    public void onCloseMenu(){
        Toast.makeText(getActivity(),"onCloseMenu", Toast.LENGTH_SHORT).show();
    }
}
