package com.action.bluetooth.view;

import com.action.bluetooth.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BluePanel extends View {

	private Paint mBorderPaint;
	private Paint mPaint2;
	private Paint mHueTrackerPaint;
	private float PALETTE_CIRCLE_TRACKER_RADIUS = 5f;
	private float RECTANGLE_TRACKER_OFFSET = 2f;
	private final static float BORDER_WIDTH_PX = 1;
	private float mDrawingOffset;
	private int mSliderTrackerColor = 0xff1c1c1c;
	private float mVal = 0f;
	private float mDensity = 1f;
	private RectF mDrawingRect;
	private Point mStartTouchPoint = null;
	private Bitmap bitmap;

	private OnSetChangedListener mListener;

	public interface OnSetChangedListener {
		public void onChangedListener(float progress);
	}

	public BluePanel(Context context) {
		super(context);
		init();
	}

	public BluePanel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BluePanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		mBorderPaint = new Paint();
		mBorderPaint.setColor(Color.RED);
		mBorderPaint.setStyle(Style.STROKE);

		mPaint2 = new Paint();

		mHueTrackerPaint = new Paint();
		mHueTrackerPaint.setColor(mSliderTrackerColor);
		mHueTrackerPaint.setStyle(Style.STROKE);
		mHueTrackerPaint.setStrokeWidth(2f * mDensity);
		mHueTrackerPaint.setAntiAlias(true);
		mDrawingOffset = calculateRequiredOffset();
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.point_blue);
	}

	private float calculateRequiredOffset() {
		float offset = Math.max(PALETTE_CIRCLE_TRACKER_RADIUS,
				RECTANGLE_TRACKER_OFFSET);
		offset = Math.max(offset, BORDER_WIDTH_PX * mDensity);

		return offset * 1.5f;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawRect(canvas);
	}

	private void drawRect(Canvas pCanvas) {

		if (BORDER_WIDTH_PX > 0) {
			mBorderPaint.setColor(Color.WHITE);
			RectF rectf = new RectF(mDrawingRect.left - BORDER_WIDTH_PX,
					mDrawingRect.top - BORDER_WIDTH_PX, mDrawingRect.right
							+ BORDER_WIDTH_PX, mDrawingRect.bottom
							+ BORDER_WIDTH_PX);
			pCanvas.drawRoundRect(rectf, 15, 15, mBorderPaint);
		}
		pCanvas.drawRoundRect(mDrawingRect, 15, 15, mBorderPaint);
		LinearGradient mGradient = new LinearGradient(mDrawingRect.left,
				mDrawingRect.top, mDrawingRect.right - 10, mDrawingRect.bottom,
				0xffffffff, 0xFF0000FF, TileMode.CLAMP);
		mPaint2.setShader(mGradient);
		pCanvas.drawRoundRect(mDrawingRect, 15, 15, mPaint2);

		Point p = ValToPoint(mVal);

		RectF rect = new RectF();
		rect.left = p.x - bitmap.getWidth() / 2 - rect.centerX();

		rect.right = p.x + bitmap.getWidth() / 2 + rect.centerX() + 20;
		rect.top = mDrawingRect.top - RECTANGLE_TRACKER_OFFSET;
		rect.bottom = mDrawingRect.bottom + RECTANGLE_TRACKER_OFFSET;
		pCanvas.drawBitmap(bitmap, rect.left, rect.top, mHueTrackerPaint);

		// pCanvas.drawRoundRect(r, 5, 5, mHueTrackerPaint);
	}

	private Point ValToPoint(float val) {

		final RectF rect = mDrawingRect;
		final float width = rect.width();

		Point p = new Point();

		p.x = (int) (val * width + rect.left);
		p.y = (int) rect.top;
		return p;

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		System.out.println("sizechanged" + "w" + w + "h" + h + "oldw" + oldw
				+ "oldh" + oldh);
		mDrawingRect = new RectF();
		mDrawingRect.left = mDrawingOffset + getPaddingLeft();
		mDrawingRect.right = w - mDrawingOffset - getPaddingRight();
		mDrawingRect.top = mDrawingOffset + getPaddingTop();
		mDrawingRect.bottom = h - mDrawingOffset - getPaddingBottom();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		boolean update = false;

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:

			mStartTouchPoint = new Point((int) event.getX(), (int) event.getY());

			update = moveTrackersIfNeeded(event);

			break;

		case MotionEvent.ACTION_MOVE:

			update = moveTrackersIfNeeded(event);

			break;

		case MotionEvent.ACTION_UP:

			mStartTouchPoint = null;

			update = moveTrackersIfNeeded(event);

			break;

		}

		if (update) {

			if (mListener != null) {
				mListener.onChangedListener(mVal);
			}
			invalidate();
			return true;
		}

		return super.onTouchEvent(event);
	}

	private boolean moveTrackersIfNeeded(MotionEvent event) {

		if (mStartTouchPoint == null)
			return false;

		boolean update = false;

		int startX = mStartTouchPoint.x;
		int startY = mStartTouchPoint.y;

		if (mDrawingRect.contains(startX, startY)) {

			mVal = pointToVal(event.getX());
			update = true;
		}

		return update;
	}

	private float pointToVal(float x) {

		final RectF rect = mDrawingRect;
		final int width = (int) rect.width();

		if (x < rect.left) {
			x = 6f;
		} else if (x >= rect.right) {
			x = width;
		} else {
			x = x - rect.left;
		}

		return 1.f / width * x;

	}

	/**
	 * Set a OnColorChangedListener to get notified when the color selected by
	 * the user has changed.
	 * 
	 * @param listener
	 */
	public void OnChangedListener(OnSetChangedListener listener) {
		mListener = listener;
	}

}
