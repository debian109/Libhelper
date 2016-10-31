package UIHelper.ViewPagerAnimation;

import android.view.View;

import UIHelper.ViewPagerAnimation.Eases.EaseType;

/**
 * animation to change the alpha of view
 */

public class WoWoAlphaAnimation extends PageAnimation {

    private EaseType easeType;
    private boolean useSameEaseTypeBack = true;

    private float targetAlpha;
    private float fromAlpha;

    /**
     *
     * @param page animation will start from this page
     * @param startOffset animation will start from this offset
     * @param endOffset animation will end at this offset
     * @param fromAlpha starting alpha
     * @param targetAlpha target alpha
     * @param easeType ease type, for more information, please check the EaseType class
     * @param useSameEaseTypeBack whether use same ease type to go back
     */
    public WoWoAlphaAnimation(
            int page,
            float startOffset,
            float endOffset,
            float fromAlpha,
            float targetAlpha,
            EaseType easeType,
            boolean useSameEaseTypeBack) {

        setPage(page);
        setStartOffset(startOffset);
        setEndOffset(endOffset);

        this.easeType = easeType;
        this.useSameEaseTypeBack = useSameEaseTypeBack;
        this.targetAlpha = targetAlpha;
        this.fromAlpha = fromAlpha;
    }

    private float lastPositionOffset = -1;

    private boolean lastTimeIsExceed = false;
    private boolean lastTimeIsLess = false;

    @Override
    public void play(View onView, float positionOffset) {

        // if the positionOffset is less than the start offset,
        // we should set onView to starting alpha
        // otherwise there may be offsets between starting alpha and actually alpha
        // notice that if the last time we have done this action, just return
        if (positionOffset <= getStartOffset()) {
            if (lastTimeIsLess) return;
            onView.setAlpha(fromAlpha);
            lastTimeIsLess = true;
            return;
        }
        lastTimeIsLess = false;

        // if the positionOffset exceeds the endOffset,
        // we should set onView to target alpha
        // otherwise there may be offsets between target alpha and actually alpha
        // notice that if the last time we have done this action, just return
        if (positionOffset >= getEndOffset()) {
            if (lastTimeIsExceed) return;
            onView.setAlpha(targetAlpha);
            lastTimeIsExceed = true;
            return;
        }
        lastTimeIsExceed = false;

        // get the true offset
        positionOffset = (positionOffset - getStartOffset()) / (getEndOffset() - getStartOffset());
        float movementOffset;

        if (lastPositionOffset == -1) {
            // first movement
            movementOffset = easeType.getOffset(positionOffset);
        } else {
            if (positionOffset < lastPositionOffset) {
                // back
                if (useSameEaseTypeBack) {
                    movementOffset = 1 - easeType.getOffset(1 - positionOffset);
                } else {
                    movementOffset = easeType.getOffset(positionOffset);
                }
            } else {
                // forward
                movementOffset = easeType.getOffset(positionOffset);
            }
        }
        lastPositionOffset = positionOffset;

        onView.setAlpha(fromAlpha + (targetAlpha - fromAlpha) * movementOffset);

    }
}
