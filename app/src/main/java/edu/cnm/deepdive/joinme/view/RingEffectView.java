package edu.cnm.deepdive.joinme.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


//todo: give attribution to https://github.com/Ramotion/circle-menu-android/blob/master/circle-menu/src/main/java/com/ramotion/circlemenu/RingEffectView.java
public class RingEffectView extends View {


  private static final int STEP_DEGREE = 5;

  private final Paint mPaint;
  private final Path mPath = new Path();

  private float mAngle;
  private float mStartAngle;
  private int mRadius;

  public RingEffectView(Context context) {
    this(context, null);
  }

  public RingEffectView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if (!mPath.isEmpty()) {
      canvas.save();
      canvas.translate(getWidth() / 2, getHeight() / 2);
      canvas.drawPath(mPath, mPaint);
      canvas.restore();
    }
  }

  @Override
  public void setAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
    mPaint.setAlpha((int) (255 * alpha));
    invalidate();
  }

  @Override
  public float getAlpha() {
    return mPaint.getAlpha() / 255;
  }

  /**
   * Gets angle.
   * @return
   */
  public float getAngle() {
    return mAngle;
  }

  /**
   * Sets angle.
   * @param angle
   */
  public void setAngle(@FloatRange(from = 0.0, to = 360.0) float angle) {
    final float diff = angle - mAngle;
    final int stepCount = (int) (diff / STEP_DEGREE);
    final float stepMod = diff % STEP_DEGREE;

    final float sw = mPaint.getStrokeWidth() * 0.5f;
    final float radius = mRadius - sw;

    for (int i = 1; i <= stepCount; i++) {
      final float stepAngel = mStartAngle + mAngle + STEP_DEGREE * i;
      final float x = (float) Math.cos(Math.toRadians(stepAngel)) * radius;
      final float y = (float) Math.sin(Math.toRadians(stepAngel)) * radius;
      mPath.lineTo(x, y);
    }

    final float stepAngel = mStartAngle + mAngle + STEP_DEGREE * stepCount + stepMod;
    final float x = (float) Math.cos(Math.toRadians(stepAngel)) * radius;
    final float y = (float) Math.sin(Math.toRadians(stepAngel)) * radius;
    mPath.lineTo(x, y);

    mAngle = angle;

    invalidate();
  }

  /**
   * Gets start angle.
   * @return
   */
  public float getStartAngle() {
    return mStartAngle;
  }

  /**
   * Sets start angle.
   * @param startAngle
   */
  public void setStartAngle(@FloatRange(from = 0.0, to = 360.0) float startAngle) {
    mStartAngle = startAngle;
    mAngle = 0;

    final float sw = mPaint.getStrokeWidth() * 0.5f;
    final float radius = mRadius - sw;

    mPath.reset();
    final float x = (float) Math.cos(Math.toRadians(startAngle)) * radius;
    final float y = (float) Math.sin(Math.toRadians(startAngle)) * radius;
    mPath.moveTo(x, y);
  }

  /**
   * Sets stroke color.
   * @param color
   */
  public void setStrokeColor(int color) {
    mPaint.setColor(color);
  }

  /**
   * Sets stroke width.
   * @param width
   */
  public void setStrokeWidth(int width) {
    mPaint.setStrokeWidth(width);
  }

  /**
   * Sets radius.
   * @param radius
   */
  public void setRadius(int radius) {
    mRadius = radius;
  }

  /**
   * Gets radius.
   * @return
   */
  public int getRadius() {
    return mRadius;
  }

}


