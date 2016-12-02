package UIHelper.GestureView.views.interfaces;

import UIHelper.GestureView.animation.ViewPositionAnimator;

/**
 * Common interface for views supporting position animation.
 */
public interface AnimatorView {

    /**
     * @return {@link ViewPositionAnimator} instance to control animation from other view position.
     */
    ViewPositionAnimator getPositionAnimator();

}
