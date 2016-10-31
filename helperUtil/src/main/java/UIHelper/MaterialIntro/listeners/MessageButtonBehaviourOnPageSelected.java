package UIHelper.MaterialIntro.listeners;

import android.util.SparseArray;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import UIHelper.MaterialIntro.MessageButtonBehaviour;
import UIHelper.MaterialIntro.adapter.SlideFragment;
import UIHelper.MaterialIntro.SlidesAdapter;
import vn.namtran.basichelper.R;

import static UIHelper.MaterialIntro.adapter.SlideFragment.isNotNullOrEmpty;


public class MessageButtonBehaviourOnPageSelected implements IPageSelectedListener {
    private Button messageButton;
    private SlidesAdapter adapter;
    private SparseArray<MessageButtonBehaviour> messageButtonBehaviours;

    public MessageButtonBehaviourOnPageSelected(Button messageButton, SlidesAdapter adapter, SparseArray<MessageButtonBehaviour> messageButtonBehaviours) {
        this.messageButton = messageButton;
        this.adapter = adapter;
        this.messageButtonBehaviours = messageButtonBehaviours;
    }

    @Override
    public void pageSelected(int position) {
        final SlideFragment slideFragment = adapter.getItem(position);

        if (slideFragment.hasAnyPermissionsToGrant()) {
            showMessageButton(slideFragment);
            messageButton.setText(slideFragment.getActivity().getString(R.string.material_intro_grant_permissions));
            messageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideFragment.askForPermissions();
                }
            });
        } else if (checkIfMessageButtonHasBehaviour(position)) {
            showMessageButton(slideFragment);
            messageButton.setText(messageButtonBehaviours.get(position).getMessageButtonText());
            messageButton.setOnClickListener(messageButtonBehaviours.get(position).getClickListener());
        } else if (messageButton.getVisibility() != View.INVISIBLE) {
            messageButton.startAnimation(AnimationUtils.loadAnimation(slideFragment.getContext(), R.anim.material_intro_fade_out));
            messageButton.setVisibility(View.INVISIBLE);

        }
    }

    private boolean checkIfMessageButtonHasBehaviour(int position) {
        return messageButtonBehaviours.get(position) != null && isNotNullOrEmpty(messageButtonBehaviours.get(position).getMessageButtonText());
    }

    private void showMessageButton(SlideFragment fragment) {
        if (messageButton.getVisibility() != View.VISIBLE) {
            messageButton.setVisibility(View.VISIBLE);
            messageButton.startAnimation(AnimationUtils.loadAnimation(fragment.getActivity(), R.anim.material_intro_fade_in));
        }
    }
}