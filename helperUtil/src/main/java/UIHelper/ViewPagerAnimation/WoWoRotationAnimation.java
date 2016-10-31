package UIHelper.ViewPagerAnimation;

import android.view.View;

import UIHelper.ViewPagerAnimation.Eases.EaseType;

/**
 * animation to change the rotate angle of view
 */

public class WoWoRotationAnimation extends PageAnimation {

    private EaseType easeType;
    private boolean useSameEaseTypeBack = true;

    private boolean pivotIsSet = false;

    private float pivotX = -1;
    private float pivotY = -1;

    private float targetX;
    private float targetY;
    private float targetZ;

    private float fromX = -1;
    private float fromY = -1;
    private float fromZ = -1;

    /**
     *        _ _ _ _ _ _ _
     *      /|    x
     *     / |
     *    /  |y
     *   /   |
     *  /z   |
     * /     |
     *
     * @param page animation will start from this page
     * @param startOffset animation will start from this offset
     * @param endOffset animation will end at this offset
     * @param pivotX the x value of the pivot of this rotation animation
     * @param pivotY the y value of the pivot of this rotation animation
     * @param targetX the target degree on x axis
     * @param targetY the target degree on y axis
     * @param targetZ the target degree on z axis
     * @param easeType ease type, for more information, please check the EaseType class
     * @param useSameEaseTypeBack whether use same ease type to go back
     */
    public WoWoRotationAnimation(
            int page,
            float startOffset,
            float endOffset,
            float pivotX,
            float pivotY,
            float targetX,
            float targetY,
            float targetZ,
            EaseType easeType,
            boolean useSameEaseTypeBack) {

        setPage(page);
        setStartOffset(startOffset);
        setEndOffset(endOffset);

        this.pivotX = pivotX;
        this.pivotY = pivotY;

        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;

        this.easeType = easeType;
        this.useSameEaseTypeBack = useSameEaseTypeBack;
    }

    /**
     * every pageAnimation has extreme rotation in X-axis, Y-axis and Z-axis
     * we have to reset the extreme to prevent the offset of rotation angle
     */
    private float extremeX = -1;
    private boolean extremeXIsSet = false;
    private float extremeY = -1;
    private boolean extremeYIsSet = false;
    private float extremeZ = -1;
    private boolean extremeZIsSet = false;

    private float lastPositionOffset = -1;

    private boolean firstTime = true;
    private boolean lastTimeIsExceed = false;

    @Override
    public void play(View onView, float positionOffset) {

        if (positionOffset < getStartOffset()) {
            return;
        }

        if (!pivotIsSet) {
            onView.setPivotX(pivotX);
            onView.setPivotY(pivotY);
            pivotIsSet = true;
        }

        if (positionOffset >= getEndOffset()) {
            // if the positionOffset exceeds the endOffset,
            // we should set onView to target angle
            // otherwise there may be offsets between target angle and actually angle
            if (lastTimeIsExceed) return;
            // if the last time we do this, just return
            onView.setRotationX(targetX);
            onView.setRotationY(targetY);
            onView.setRotation(targetZ);
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

            fromX = onView.getRotationX();

            if (!extremeXIsSet) {
                extremeXIsSet = true;
                extremeX = fromX;
            } else {
                fromX = extremeX;
            }

            fromY = onView.getRotationY();

            if (!extremeYIsSet) {
                extremeYIsSet = true;
                extremeY = fromY;
            } else {
                fromY = extremeY;
            }

            fromZ = onView.getRotation();

            if (!extremeZIsSet) {
                extremeZIsSet = true;
                extremeZ = fromZ;
            } else {
                fromZ = extremeZ;
            }

            return;
        }

        onView.setRotationX(fromX + (targetX - fromX) * movementOffset);
        onView.setRotationY(fromY + (targetY - fromY) * movementOffset);
        onView.setRotation(fromZ + (targetZ - fromZ) * movementOffset);

    }
}
