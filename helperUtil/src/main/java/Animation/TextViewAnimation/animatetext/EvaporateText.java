package Animation.TextViewAnimation.animatetext;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.animation.AccelerateDecelerateInterpolator;

import JavaHelper.CharacterUtils;


/**
 * Evaporation effect
 */
public class EvaporateText extends HText {

    float charTime  = 300; //Each character animation time 500ms
    int   mostCount = 20; // Up to 10 characters simultaneously Animation
    private int   mTextHeight;
    private float progress;

    @Override protected void initVariables() {

    }

    @Override protected void animateStart(CharSequence text) {
        int n = mText.length();
        n = n <= 0 ? 1 : n;

        // Calculate the total time animation
        long duration = (long) (charTime + charTime / mostCount * (n - 1));

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, duration).setDuration(duration);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                mHTextView.invalidate();
            }
        });
        valueAnimator.start();
    }

    @Override protected void animatePrepare(CharSequence text) {

        Rect bounds = new Rect();
        mPaint.getTextBounds(mText.toString(), 0, mText.length(), bounds);
        mTextHeight = bounds.height();
    }

    @Override protected void drawFrame(Canvas canvas) {

    }

    @Override public void onDraw(Canvas canvas) {
        float offset = startX;
        float oldOffset = oldStartX;

        int maxLength = Math.max(mText.length(), mOldText.length());

        for (int i = 0; i < maxLength; i++) {

            // draw old text
            if (i < mOldText.length()) {
                //
                float pp = progress / (charTime + charTime / mostCount * (mText.length() - 1));

                mOldPaint.setTextSize(mTextSize);
                int move = CharacterUtils.needMove(i, differentList);
                if (move != -1) {
                    mOldPaint.setAlpha(255);
                    float p = pp * 2f;
                    p = p > 1 ? 1 : p;
                    float distX = CharacterUtils.getOffset(i, move, p, startX, oldStartX, gaps, oldGaps);
                    canvas.drawText(mOldText.charAt(i) + "", 0, 1, distX, startY, mOldPaint);
                } else {
                    mOldPaint.setAlpha((int) ((1 - pp) * 255));
                    float y = startY - pp * mTextHeight;
                    float width = mOldPaint.measureText(mOldText.charAt(i) + "");
                    canvas.drawText(mOldText.charAt(i) + "", 0, 1, oldOffset + (oldGaps[i] - width) / 2, y, mOldPaint);
                }
                oldOffset += oldGaps[i];
            }

            // draw new text
            if (i < mText.length()) {

                if (!CharacterUtils.stayHere(i, differentList)) {

                    int alpha = (int) (255f / charTime * (progress - charTime * i / mostCount));
                    alpha = alpha > 255 ? 255 : alpha;
                    alpha = alpha < 0 ? 0 : alpha;


                    mPaint.setAlpha(alpha);
                    mPaint.setTextSize(mTextSize);
                    float pp = progress / (charTime + charTime / mostCount * (mText.length() - 1));
                    float y = mTextHeight + startY - pp * mTextHeight;

                    float width = mPaint.measureText(mText.charAt(i) + "");
                    canvas.drawText(mText.charAt(i) + "", 0, 1, offset + (gaps[i] - width) / 2, y, mPaint);
                }

                offset += gaps[i];
            }
        }
    }

}
