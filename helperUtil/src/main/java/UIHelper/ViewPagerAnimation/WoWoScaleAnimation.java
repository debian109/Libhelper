package UIHelper.ViewPagerAnimation;

import android.view.View;
import android.view.ViewGroup;

import UIHelper.ViewPagerAnimation.Eases.EaseType;

/**
 * animation to translate view's position
 */
public class WoWoScaleAnimation extends PageAnimation {

    private EaseType easeType;
    private boolean useSameEaseTypeBack = true;

    private float targetScaleX;
    private float targetScaleY;
    private float fromWidth;
    private float fromHeight;

    /**
     *
     * @param page animation will start from this page
     * @param startOffset animation will start from this offset
     * @param endOffset animation will end at this offset
     * @param targetScaleX target scale x = target x / original y
     * @param targetScaleY target scale y = target y / original y
     * @param easeType ease type, for more information, please check the EaseType class
     * @param useSameEaseTypeBack whether use same ease type to go back
     */
    public WoWoScaleAnimation(
            int page,
            float startOffset,
            float endOffset,
            float targetScaleX,
            float targetScaleY,
            EaseType easeType,
            boolean useSameEaseTypeBack) {

        setPage(page);
        setStartOffset(startOffset);
        setEndOffset(endOffset);

        this.easeType = easeType;
        this.useSameEaseTypeBack = useSameEaseTypeBack;
        this.targetScaleX = targetScaleX;
        this.targetScaleY = targetScaleY;
        fromWidth = -1;
        fromHeight = -1;
    }

    /**
     * every pageAnimation has extreme scale of width and height
     * we have to reset the extreme to prevent the offset of size
     */
    private float extremeWidth = -1;
    private boolean extremeWidthIsSet = false;

    private float extremeHeight = -1;
    private boolean extremeHeightIsSet = false;

    private float lastPositionOffset = -1;

    private boolean firstTime = true;
    private boolean lastTimeIsExceed = false;

    @Override
    public void play(View onView, float positionOffset) {

        if (positionOffset < getStartOffset()) {
            return;
        }

        if (positionOffset >= getEndOffset()) {
            // if the positionOffset exceeds the endOffset,
            // we should set onView to targetSize
            // otherwise there may be offsets between targetSize and actuallySize
            if (lastTimeIsExceed) return;
            // if the last time we do this, just return
            ViewGroup.LayoutParams param = onView.getLayoutParams();
            param.width = (int)(fromWidth * targetScaleX);
            param.height = (int)(fromHeight * targetScaleY);
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

        if (firstTime) {
            firstTime = false;

            fromWidth = onView.getLayoutParams().width;
            fromHeight = onView.getLayoutParams().height;

            if (!extremeHeightIsSet) {
                extremeHeightIsSet = true;
                extremeHeight = fromHeight;
            } else {
                fromHeight = extremeHeight;
            }

            if (!extremeWidthIsSet) {
                extremeWidthIsSet = true;
                extremeWidth = fromWidth;
            } else {
                fromWidth = extremeWidth;
            }

            return;
        }

        ViewGroup.LayoutParams param = onView.getLayoutParams();
        param.width = (int)(fromWidth + (targetScaleX - 1) * fromWidth * movementOffset);
        param.height = (int)(fromHeight + (targetScaleY - 1) * fromHeight * movementOffset);
        onView.setLayoutParams(param);

    }
}
