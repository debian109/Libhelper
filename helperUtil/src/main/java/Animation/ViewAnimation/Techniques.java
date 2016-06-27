
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package Animation.ViewAnimation;

import java.util.ArrayList;
import java.util.List;

import Animation.ViewAnimation.attention.BounceAnimator;
import Animation.ViewAnimation.attention.FlashAnimator;
import Animation.ViewAnimation.attention.PulseAnimator;
import Animation.ViewAnimation.attention.RubberBandAnimator;
import Animation.ViewAnimation.attention.ShakeAnimator;
import Animation.ViewAnimation.attention.StandUpAnimator;
import Animation.ViewAnimation.attention.SwingAnimator;
import Animation.ViewAnimation.attention.TadaAnimator;
import Animation.ViewAnimation.attention.WaveAnimator;
import Animation.ViewAnimation.attention.WobbleAnimator;
import Animation.ViewAnimation.bouncing_entrances.BounceInAnimator;
import Animation.ViewAnimation.bouncing_entrances.BounceInDownAnimator;
import Animation.ViewAnimation.bouncing_entrances.BounceInLeftAnimator;
import Animation.ViewAnimation.bouncing_entrances.BounceInRightAnimator;
import Animation.ViewAnimation.bouncing_entrances.BounceInUpAnimator;
import Animation.ViewAnimation.fading_entrances.FadeInAnimator;
import Animation.ViewAnimation.fading_entrances.FadeInDownAnimator;
import Animation.ViewAnimation.fading_entrances.FadeInLeftAnimator;
import Animation.ViewAnimation.fading_entrances.FadeInRightAnimator;
import Animation.ViewAnimation.fading_entrances.FadeInUpAnimator;
import Animation.ViewAnimation.fading_exits.FadeOutAnimator;
import Animation.ViewAnimation.fading_exits.FadeOutDownAnimator;
import Animation.ViewAnimation.fading_exits.FadeOutLeftAnimator;
import Animation.ViewAnimation.fading_exits.FadeOutRightAnimator;
import Animation.ViewAnimation.fading_exits.FadeOutUpAnimator;
import Animation.ViewAnimation.flippers.FlipInXAnimator;
import Animation.ViewAnimation.flippers.FlipInYAnimator;
import Animation.ViewAnimation.flippers.FlipOutXAnimator;
import Animation.ViewAnimation.flippers.FlipOutYAnimator;
import Animation.ViewAnimation.rotating_entrances.RotateInAnimator;
import Animation.ViewAnimation.rotating_entrances.RotateInDownLeftAnimator;
import Animation.ViewAnimation.rotating_entrances.RotateInDownRightAnimator;
import Animation.ViewAnimation.rotating_entrances.RotateInUpLeftAnimator;
import Animation.ViewAnimation.rotating_entrances.RotateInUpRightAnimator;
import Animation.ViewAnimation.rotating_exits.RotateOutAnimator;
import Animation.ViewAnimation.rotating_exits.RotateOutDownLeftAnimator;
import Animation.ViewAnimation.rotating_exits.RotateOutDownRightAnimator;
import Animation.ViewAnimation.rotating_exits.RotateOutUpLeftAnimator;
import Animation.ViewAnimation.rotating_exits.RotateOutUpRightAnimator;
import Animation.ViewAnimation.sliders.SlideInDownAnimator;
import Animation.ViewAnimation.sliders.SlideInLeftAnimator;
import Animation.ViewAnimation.sliders.SlideInRightAnimator;
import Animation.ViewAnimation.sliders.SlideInUpAnimator;
import Animation.ViewAnimation.sliders.SlideOutDownAnimator;
import Animation.ViewAnimation.sliders.SlideOutLeftAnimator;
import Animation.ViewAnimation.sliders.SlideOutRightAnimator;
import Animation.ViewAnimation.sliders.SlideOutUpAnimator;
import Animation.ViewAnimation.zooming_entrances.ZoomInAnimator;
import Animation.ViewAnimation.zooming_entrances.ZoomInDownAnimator;
import Animation.ViewAnimation.zooming_entrances.ZoomInLeftAnimator;
import Animation.ViewAnimation.zooming_entrances.ZoomInRightAnimator;
import Animation.ViewAnimation.zooming_entrances.ZoomInUpAnimator;
import Animation.ViewAnimation.zooming_exits.ZoomOutAnimator;
import Animation.ViewAnimation.zooming_exits.ZoomOutDownAnimator;
import Animation.ViewAnimation.zooming_exits.ZoomOutLeftAnimator;
import Animation.ViewAnimation.zooming_exits.ZoomOutRightAnimator;
import Animation.ViewAnimation.zooming_exits.ZoomOutUpAnimator;

public enum Techniques {

    Flash(FlashAnimator.class),
    Pulse(PulseAnimator.class),
    RubberBand(RubberBandAnimator.class),
    Shake(ShakeAnimator.class),
    Swing(SwingAnimator.class),
    Wobble(WobbleAnimator.class),
    Bounce(BounceAnimator.class),
    Tada(TadaAnimator.class),
    StandUp(StandUpAnimator.class),
    Wave(WaveAnimator.class),

    BounceIn(BounceInAnimator.class),
    BounceInDown(BounceInDownAnimator.class),
    BounceInLeft(BounceInLeftAnimator.class),
    BounceInRight(BounceInRightAnimator.class),
    BounceInUp(BounceInUpAnimator.class),

    FadeIn(FadeInAnimator.class),
    FadeInUp(FadeInUpAnimator.class),
    FadeInDown(FadeInDownAnimator.class),
    FadeInLeft(FadeInLeftAnimator.class),
    FadeInRight(FadeInRightAnimator.class),

    FadeOut(FadeOutAnimator.class),
    FadeOutDown(FadeOutDownAnimator.class),
    FadeOutLeft(FadeOutLeftAnimator.class),
    FadeOutRight(FadeOutRightAnimator.class),
    FadeOutUp(FadeOutUpAnimator.class),

    FlipInX(FlipInXAnimator.class),
    FlipOutX(FlipOutXAnimator.class),
    FlipInY(FlipInYAnimator.class),
    FlipOutY(FlipOutYAnimator.class),
    RotateIn(RotateInAnimator.class),
    RotateInDownLeft(RotateInDownLeftAnimator.class),
    RotateInDownRight(RotateInDownRightAnimator.class),
    RotateInUpLeft(RotateInUpLeftAnimator.class),
    RotateInUpRight(RotateInUpRightAnimator.class),

    RotateOut(RotateOutAnimator.class),
    RotateOutDownLeft(RotateOutDownLeftAnimator.class),
    RotateOutDownRight(RotateOutDownRightAnimator.class),
    RotateOutUpLeft(RotateOutUpLeftAnimator.class),
    RotateOutUpRight(RotateOutUpRightAnimator.class),

    SlideInLeft(SlideInLeftAnimator.class),
    SlideInRight(SlideInRightAnimator.class),
    SlideInUp(SlideInUpAnimator.class),
    SlideInDown(SlideInDownAnimator.class),

    SlideOutLeft(SlideOutLeftAnimator.class),
    SlideOutRight(SlideOutRightAnimator.class),
    SlideOutUp(SlideOutUpAnimator.class),
    SlideOutDown(SlideOutDownAnimator.class),

    ZoomIn(ZoomInAnimator.class),
    ZoomInDown(ZoomInDownAnimator.class),
    ZoomInLeft(ZoomInLeftAnimator.class),
    ZoomInRight(ZoomInRightAnimator.class),
    ZoomInUp(ZoomInUpAnimator.class),

    ZoomOut(ZoomOutAnimator.class),
    ZoomOutDown(ZoomOutDownAnimator.class),
    ZoomOutLeft(ZoomOutLeftAnimator.class),
    ZoomOutRight(ZoomOutRightAnimator.class),
    ZoomOutUp(ZoomOutUpAnimator.class);



    private Class animatorClazz;

    private Techniques(Class clazz) {
        animatorClazz = clazz;
    }

    public BaseViewAnimator getAnimator() {
        try {
            return (BaseViewAnimator) animatorClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }

    public static List<Techniques> getListTechniques()
    {
        List<Techniques> techniques = new ArrayList<>();
        techniques.add(Techniques.Flash);
        techniques.add(Techniques.Bounce);
        techniques.add(Techniques.BounceIn);
        techniques.add(Techniques.BounceInDown);
        techniques.add(Techniques.BounceInLeft);
        techniques.add(Techniques.BounceInRight);
        techniques.add(Techniques.BounceInUp);
        techniques.add(Techniques.FadeIn);
        techniques.add(Techniques.FadeInDown);
        techniques.add(Techniques.ZoomInUp);
        techniques.add(Techniques.ZoomInRight);
        techniques.add(Techniques.ZoomInLeft);
        techniques.add(Techniques.ZoomInDown);
        techniques.add(Techniques.Wobble);
        techniques.add(Techniques.Wave);
        techniques.add(Techniques.Tada);
        techniques.add(Techniques.Swing);
        techniques.add(Techniques.StandUp);
        techniques.add(Techniques.SlideInUp);
        techniques.add(Techniques.SlideInRight);
        techniques.add(Techniques.SlideInLeft);
        techniques.add(Techniques.SlideInDown);
        techniques.add(Techniques.Shake);
        techniques.add(Techniques.RubberBand);
        techniques.add(Techniques.RotateInUpRight);
        techniques.add(Techniques.RotateInUpLeft);
        techniques.add(Techniques.RotateInDownRight);
        techniques.add(Techniques.RotateInDownLeft);
        techniques.add(Techniques.Pulse);
        techniques.add(Techniques.FlipInY);
        techniques.add(Techniques.FlipInX);
        techniques.add(Techniques.FadeInUp);
        techniques.add(Techniques.FadeInLeft);
        techniques.add(Techniques.FadeInRight);
        return techniques;
    }
}
