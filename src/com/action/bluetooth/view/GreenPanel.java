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

public class GreenPanel extends View {
	private Paint mBorderPaint;
	private Paint mPaint2;
	private Paint mHueTrackerPaint;
	private float PALETTE_CIRCLE_TRACKER_RADIUS = 5f;
	/**
	 * The dp which the tracker of the hue or alpha panel will extend outside of
	 * its bounds.
	 */
	private float RECTANGLE_TRACKER_OFFSET = 2f;
	private final static float BORDER_WIDTH_PX = 1;
	private float mDrawingOffset;
	private int mSliderTrackerColor = 0xff1c1c1c;
	private float mSat = 0f;
	private float mDensity = 1f;
	private RectF mDrawingRect;
	private Point mStartTouchPoint = null;
	private int mColor = Color.HSVToColor(new float[] { 360, 1f, 1f });

	private OnSatChangedListener mListener;
	private Bitmap bitmap;

	public interface OnSatChangedListener {
		public void onColorChanged(float sat);
	}

	public GreenPanel(Context context) {
		super(context);
		init();
	}

	public GreenPanel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public GreenPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		mBorderPaint = new Paint();
		mBorderPaint.setColor(Color.GRAY);
		mBorderPaint.setStyle(Style.STROKE);

		mPaint2 = new Paint();

		mHueTrackerPaint = new Paint();
		mHueTrackerPaint.setColor(mSliderTrackerColor);
		mHueTrackerPaint.setStyle(Style.STROKE);
		mHueTrackerPaint.setStrokeWidth(2f * mDensity);
		mHueTrackerPaint.setAntiAlias(true);
		mDrawingOffset = calculateRequiredOffset();
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.point_green);
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
			RectF rectf = new RectF(mDrawingRect.left - BORDER_WIDTH_PX, mDrawingRect.top - BORDER_WIDTH_PX, mDrawingRect.right
					+ BORDER_WIDTH_PX, mDrawingRect.bottom
					+ BORDER_WIDTH_PX);
			pCanvas.drawRoundRect(rectf,15,15, mBorderPaint);
		}
		pCanvas.drawRoundRect(mDrawingRect,15,15, mBorderPaint);

		LinearGradient mGradient = new LinearGradient(mDrawingRect.left,
				mDrawingRect.top, mDrawingRect.right, mDrawingRect.bottom,
				0xff00ff00,0xffffffff,  TileMode.CLAMP);
		mPaint2.setShader(mGradient);
		pCanvas.drawRoundRect(mDrawingRect,15,15, mPaint2);

		Point p = SatToPoint(mSat);

		float rectWidth = 4 * mDensity / 2;

		RectF r = new RectF();
		r.left = p.x - bitmap.getWidth()/2;
		r.right = p.x + bitmap.getWidth()/2;
		r.top = mDrawingRect.top - RECTANGLE_TRACKER_OFFSET;
		r.bottom = mDrawingRect.bottom + RECTANGLE_TRACKER_OFFSET;
		
		pCanvas.drawBitmap(bitmap, r.left, r.top, mHueTrackerPaint);

		//pCanvas.drawRoundRect(r, 5, 5, mHueTrackerPaint);
	}


	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
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
				mListener.onColorChanged(mSat);
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

			mSat = pointToSat(event.getX());
			update = true;
		}

		return update;
	}
	
	private Point SatToPoint(float sat) {

		final RectF rect = mDrawingRect;
		final float width = rect.width();

		Point p = new Point();

		p.x = (int) (sat*width+rect.left);
		p.y = (int) rect.top;
		return p;

	}
	

	private float pointToSat(float x) {

		final RectF rect = mDrawingRect;
		final int width = (int) rect.width();

		if (x < rect.left) {
			x = 0f;
		} else if (x > rect.right) {
			x = width;
		} else {
			x = x - rect.left;
		}

		return 1.f / width * x;

	}
/*
	private int[] buildHueColorArray() {

		int[] hue = new int[361];

		int count = 0;
		for (int i = hue.length - 1; i >= 0; i--, count++) {
			hue[count] = Color.HSVToColor(new float[] { i, 1f, 1f });
		}

		return hue;
	}*/

	/**
	 * Set a OnColorChangedListener to get notified when the color selected by
	 * the user has changed.
	 * 
	 * @param listener
	 */
	public void setOnSatrChangedListener(OnSatChangedListener listener) {
		mListener = listener;
	}
	public void setColor(int color){
		mColor = color;
		invalidate();
	}


}
