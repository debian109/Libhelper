package UIHelper.ViewPagerAnimation;

import android.view.View;

import UIHelper.ViewPagerAnimation.Eases.EaseType;

/**
 * animation to change the WoWoPathView
 */

public class WoWoPathAnimation extends PageAnimation {

    private EaseType easeType;
    private boolean useSameEaseTypeBack = true;

    /**
     *
     * @param page animation starting page
     * @param startOffset animation starting offset
     * @param endOffset animation ending offset
     * @param easeType ease type.
     *                 For more information, please check the EaseType.class
     * @param useSameEaseTypeBack whether use the same ease type to back
     */
    public WoWoPathAnimation(
            int page,
            float startOffset,
            float endOffset,
            EaseType easeType,
            boolean useSameEaseTypeBack) {

        setPage(page);
        setStartOffset(startOffset);
        setEndOffset(endOffset);

        this.easeType = easeType;
        this.useSameEaseTypeBack = useSameEaseTypeBack;
    }

    private float lastPositionOffset = -1;

    private boolean firstTime = true;
    private boolean lastTimeIsExceed = false;
    private boolean lastTimeIsLess = false;

    @Override
    public void play(View onView, float positionOffset) {

        // if the positionOffset is less than the starting percent,
        // we should set onView to starting percent
        // otherwise there may be offsets between starting percent and actually percent
        // if the last time we do this, just return
        if (positionOffset <= getStartOffset()) {
            if (lastTimeIsLess) return;
            if (onView instanceof WoWoPathView) {
                ((WoWoPathView)onView).setPercentage(0);
            }
            lastTimeIsLess = true;
            return;
        }
        lastTimeIsLess = false;

        // if the positionOffset exceeds the end percent,
        // we should set onView to target percent
        // otherwise there may be offsets between target alpha and actually alpha
        // if the last time we do this, just return
        if (positionOffset >= getEndOffset()) {
            if (lastTimeIsExceed) return;
            if (onView instanceof WoWoPathView) {
                ((WoWoPathView)onView).setPercentage(1);
            }
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

        if (onView instanceof WoWoPathView) {
            ((WoWoPathView)onView).setPercentage(movementOffset);
        }
    }
}
