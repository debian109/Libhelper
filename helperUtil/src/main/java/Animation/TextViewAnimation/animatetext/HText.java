package Animation.TextViewAnimation.animatetext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import Animation.TextViewAnimation.HTextView;
import JavaHelper.CharacterDiffResult;
import JavaHelper.CharacterUtils;

/**
 * base class
 */
public abstract class HText implements IHText {

    protected Paint mPaint, mOldPaint;

    /**
     * the gap between characters
     */
    protected float[] gaps    = new float[100];
    protected float[] oldGaps = new float[100];

    /**
     * current text size
     */
    protected float mTextSize;

    protected CharSequence mText;
    protected CharSequence mOldText;

    protected List<CharacterDiffResult> differentList = new ArrayList<>();

    protected float oldStartX = 0; // The original string began to draw the x position
    protected float startX    = 0; // New string began to draw the x position
    protected float startY    = 0; // He began to draw the string y, baseline

    protected HTextView mHTextView;


    @Override public void init(HTextView hTextView, AttributeSet attrs, int defStyle) {

        mHTextView = hTextView;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mHTextView.getCurrentTextColor());
        mPaint.setStyle(Paint.Style.FILL);

        mOldPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOldPaint.setColor(mHTextView.getCurrentTextColor());
        mOldPaint.setStyle(Paint.Style.FILL);

        mText = mHTextView.getText();
        mOldText = mHTextView.getText();

        mTextSize = mHTextView.getTextSize();

        initVariables();
        mHTextView.postDelayed(new Runnable() {
            @Override public void run() {
                prepareAnimate();
            }
        },50);

    }

    @Override public void animateText(CharSequence text) {
        mHTextView.setText(text);
        mOldText = mText;
        mText = text;
        prepareAnimate();
        animatePrepare(text);
        animateStart(text);
    }

    @Override public void onDraw(Canvas canvas) {
        drawFrame(canvas);
    }

    private void prepareAnimate() {
        mTextSize = mHTextView.getTextSize();

        mPaint.setTextSize(mTextSize);
        for (int i = 0; i < mText.length(); i++) {
            gaps[i] = mPaint.measureText(mText.charAt(i) + "");
        }

        mOldPaint.setTextSize(mTextSize);
        for (int i = 0; i < mOldText.length(); i++) {
            oldGaps[i] = mOldPaint.measureText(mOldText.charAt(i) + "");
        }

        oldStartX = (mHTextView.getMeasuredWidth() - mHTextView.getCompoundPaddingLeft() - mHTextView.getPaddingLeft() - mOldPaint
                .measureText(mOldText.toString())) / 2f;
        startX = (mHTextView.getMeasuredWidth() - mHTextView.getCompoundPaddingLeft() - mHTextView.getPaddingLeft() - mPaint
                .measureText(mText.toString())) / 2f;
        startY = mHTextView.getBaseline();

        differentList.clear();
        differentList.addAll(CharacterUtils.diff(mOldText, mText));
    }

    public void reset(CharSequence text) {
        animatePrepare(text);
        mHTextView.invalidate();
    }

    /**
     * Instantiated class is initialized
     */
    protected abstract void initVariables();
    /**
     * Implementation Animation
     * @param text
     */
    protected abstract void animateStart(CharSequence text);

    /**
     * Before each initialization call animation
     * @param text
     */
    protected abstract void animatePrepare(CharSequence text);

    /**
     * Movies Each time refreshed interface call
     * @param canvas
     */
    protected abstract void drawFrame(Canvas canvas);



}
