package UIHelper.ViewPagerAnimation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import vn.namtran.basichelper.R;

/**
 * this view helps to create a path animation
 * including a image as its head
 */

public class WoWoPathView extends View {

    private Paint mPaint;
    private Path mPath;
    private int mPathColor;
    private float mPathWidth;
    private float mProgress = 0f;
    private float mPathLength = 0f;
    private PathMeasure mPathMeasure;

    private int headImageId = 0;
    private Bitmap mBitmap;
    private float mBitmapWidth = -1, mBitmapHeight = -1;
    private int mBitmapOffsetX, mBitmapOffsetY;
    private float[] mBitmapPosition;
    private float[] mBitmapTan;
    private Matrix mMatrix;

    public WoWoPathView(Context context) {
        this(context, null);
        init();
    }

    public WoWoPathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public WoWoPathView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WoWoPathView);
        mPathColor = a.getColor(R.styleable.WoWoPathView_pathColor, 0xff00ff00);
        mPathWidth = a.getFloat(R.styleable.WoWoPathView_pathWidth, 8.0f);
        headImageId = a.getResourceId(R.styleable.WoWoPathView_headImageId, 0);
        mBitmapWidth = a.getFloat(R.styleable.WoWoPathView_headImageWidth, -1);
        mBitmapHeight = a.getFloat(R.styleable.WoWoPathView_headImageHeight, -1);

        a.recycle();

        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(mPathColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mPathWidth);
        mPaint.setAntiAlias(true);

        // no head
        if (headImageId != 0) {
            mBitmap = BitmapFactory.decodeResource(getResources(), headImageId);

            if (mBitmapWidth != -1 || mBitmapHeight != -1) {
                mBitmap = getResizedBitmap(mBitmap, mBitmapWidth, mBitmapHeight);
            }

            mBitmapOffsetX = mBitmap.getWidth() / 2;
            mBitmapOffsetY = mBitmap.getHeight() / 2;

            mBitmapPosition = new float[2];
            mBitmapTan = new float[2];
            mMatrix = new Matrix();
        }

        setPath(new Path());
    }

    private Bitmap getResizedBitmap(Bitmap bm, float newWidth, float newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = 1, scaleHeight = 1;
        if (newWidth != -1) {
            scaleWidth = newWidth / width;
            if (newHeight != -1) {
                scaleHeight = newHeight / height;
            } else {
                scaleHeight = scaleWidth;
            }
        } else {
            if (newHeight != -1) {
                scaleWidth = scaleHeight = newHeight / height;
            }
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    public void setPath(Path p){
        mPath = p;
        mPathMeasure = new PathMeasure(mPath, false);
        mPathLength = mPathMeasure.getLength();
    }

    public void setPercentage(float percentage){
        if(percentage < 0.0f || percentage > 1.0f) return;

        mProgress = percentage;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        PathEffect pathEffect = new DashPathEffect(
                new float[]{mPathLength, mPathLength},
                (mPathLength - mPathLength * mProgress));
        mPaint.setPathEffect(pathEffect);

        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        canvas.drawPath(mPath, mPaint);

        if (headImageId != 0) {
            mPathMeasure.getPosTan(mPathLength * mProgress, mBitmapPosition, mBitmapTan);

            mMatrix.reset();
            float degrees = (float) (Math.atan2(mBitmapTan[1], mBitmapTan[0]) * 180.0 / Math.PI);
            mMatrix.postRotate(degrees, mBitmapOffsetX, mBitmapOffsetY);
            mMatrix.postTranslate(
                    mBitmapPosition[0] - mBitmapOffsetX,
                    mBitmapPosition[1] - mBitmapOffsetY);

            canvas.drawBitmap(mBitmap, mMatrix, null);
        }

        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);

        int measuredWidth, measuredHeight;

        if(widthMode == MeasureSpec.AT_MOST)
            throw new IllegalStateException("WoWoPathView cannot have a WRAP_CONTENT property");
        else
            measuredWidth = widthSize;

        if(heightMode == MeasureSpec.AT_MOST)
            throw new IllegalStateException("WoWoPathView cannot have a WRAP_CONTENT property");
        else
            measuredHeight = heightSize;

        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
