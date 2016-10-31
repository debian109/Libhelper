package UIHelper.ViewPagerAnimation;

import android.view.View;

import UIHelper.ViewPagerAnimation.Eases.EaseType;

/**
 * animation to translate view's position
 */

public class WoWoTranslationAnimation extends PageAnimation {

    private EaseType easeType;
    private boolean useSameEaseTypeBack = true;

    private float targetX;
    private float targetY;
    private float fromX;
    private float fromY;

    /**
     *
     * @param page animation starting page
     * @param startOffset animation starting offset
     * @param endOffset animation ending offset
     * @param fromX the starting horizontal position of this view relative to its left position,
     *              in pixels.
     * @param fromY the starting vertical position of this view relative to its top position,
     *              in pixels.
     * @param targetX the ending horizontal position of this view relative to its left position,
     *              in pixels.
     * @param targetY the ending vertical position of this view relative to its top position,
     *              in pixels.
     * @param easeType ease type.
     *                 For more information, please check the EaseType.class
     * @param useSameEaseTypeBack whether use the same ease type to back
     */
    public WoWoTranslationAnimation(
            int page,
            float startOffset,
            float endOffset,
            float fromX,
            float fromY,
            float targetX,
            float targetY,
            EaseType easeType,
            boolean useSameEaseTypeBack) {

        setPage(page);
        setStartOffset(startOffset);
        setEndOffset(endOffset);

        this.easeType = easeType;
        this.useSameEaseTypeBack = useSameEaseTypeBack;
        this.targetX = targetX;
        this.targetY = targetY;
        this.fromX = fromX;
        this.fromY = fromY;
    }

    private float lastPositionOffset = -1;

    private boolean lastTimeIsExceed = false;
    private boolean lastTimeIsLess = false;

    @Override
    public void play(View onView, float positionOffset) {

        // if the positionOffset is less than the start offset,
        // we should set onView to start position
        // otherwise there may be offsets between targetPosition and actuallyPosition
        // notice that if the last time we have done this, just return
        if (positionOffset <= getStartOffset()) {
            if (lastTimeIsLess) return;
            onView.setTranslationX(fromX);
            onView.setTranslationY(fromY);
            onView.requestLayout();
            lastTimeIsLess = true;
            return;
        }
        lastTimeIsLess = false;

        // if the positionOffset exceeds the endOffset,
        // we should set onView to target position
        // otherwise there may be offsets between target position and actually position
        // notice that if the last time we have done this, just return
        if (positionOffset >= getEndOffset()) {
            if (lastTimeIsExceed) return;
            onView.setTranslationX(targetX + fromX);
            onView.setTranslationY(targetY + fromY);
            onView.requestLayout();
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

        onView.setTranslationX(targetX * movementOffset + fromX);
        onView.setTranslationY(targetY * movementOffset + fromY);
        onView.requestLayout();

    }
}
