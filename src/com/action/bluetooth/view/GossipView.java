 package com.action.bluetooth.view;

import java.util.List;

import com.action.bluetooth.R;
import com.action.bluetooth.until.GossipItem;
import com.action.bluetooth.until.MyUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GossipView extends View {

	public class Point {
		public float x;
		public float y;

		public Point(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}

	public interface OnPieceClickListener {
		void onPieceClick(int whitchPiece);
	}

	private static final String TAG = "com.jcodecraeer.gossipview";
	private RectF mOuterArcRectangle = new RectF();
	private RectF mInnerArcRectangle = new RectF();
	private float mOuterArcRadius;
	private float mInnerArcRadius;
	private Paint mOuterArcPaint;
	private Paint mInnerArcPaint;
	private Paint mOuterTextPaint;
	private Paint mNumberTextPaint;
	private Paint mProgressPaint;
	private float outArctrokeWidth;
	private float mInnerArctrokeWidth;
	private int mPieceNumber = 12;
	private int mPieceDegree = 360 / mPieceNumber;
	private int mDividerDegree = 0;
	private int mWidth;
	private Drawable mInnerBackGroud;
	private Drawable mOuterBackGroud;
	private int mSelectIndex = -2;
	private int mNumber;
	private int[] outArcColor = { 0xff28813a, 0xff50aa40, 0xffa5c641,0xff9cc88a,
			0xffdaa520, 0xff3086be, 0xff61418f, 0xff87ceeb, 0xffdc99bf,
			0xffcd5c5c, 0xffff7f50, 0xffdf5e47};
	private Context mContext;
	private SweepGradient mSweepGradient;
	/** Extended distance, Click on the effect of increased peripheral button */
	private int overTouchDistance = MyUtils.dip2px(getContext(), 15);
	private int progressAnimateStartAngle = 0; // The animation
	private int padding = MyUtils.dip2px(getContext(), 10);

	private List<GossipItem> items;
	private static int HOME_NUMBER_TEXT_SIZE = 25;
	private static float mScale = 0; // Used for supporting different screen
										// densities
	private OnPieceClickListener mListener;

	public GossipView(Context context) {
		super(context);
		init(context, null, 0);
	}

	public GossipView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public GossipView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	private void init(Context c, AttributeSet attrs, int defStyle) {

		mContext = c;
		if (mScale == 0) {
			mScale = getContext().getResources().getDisplayMetrics().density;
			Log.i(TAG, "mScale = " + mScale);
			if (mScale != 1) {
				HOME_NUMBER_TEXT_SIZE *= mScale;
			}
		}
		mOuterArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mOuterArcPaint.setStyle(Paint.Style.STROKE);

		mInnerArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mInnerArcPaint.setStyle(Paint.Style.STROKE);
		mInnerArcPaint.setColor(0xfff39700);

		mOuterTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mOuterTextPaint.setColor(0xffffffff);
		mOuterTextPaint.setTextSize(outArctrokeWidth / 8);
		mOuterTextPaint.setAntiAlias(true);

		mNumberTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mNumberTextPaint.setColor(0xff076291);
		mNumberTextPaint.setTextSize(HOME_NUMBER_TEXT_SIZE);
		mInnerBackGroud = mContext.getResources().getDrawable(
				R.drawable.home_score_bg_selector);
		mOuterBackGroud = mContext.getResources().getDrawable(
				R.drawable.home_score_normal_bg);
		mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mProgressPaint.setStyle(Paint.Style.STROKE);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mOuterBackGroud.draw(canvas);
		for (int i = 0; i < mPieceNumber; i++) {
			drawArc(i, canvas);
		}
		if ((mSelectIndex == -1)||(mSelectIndex == -2)) {
			Log.i(TAG, "mSelectIndex = " + mSelectIndex);
			mInnerBackGroud.setState(PRESSED_FOCUSED_STATE_SET);
		} else {
			mInnerBackGroud.setState(EMPTY_STATE_SET);
		}
		mInnerBackGroud.draw(canvas);

		// Draw round the base
		mInnerArcPaint.setColor(0xffffffe5);
		canvas.drawArc(mInnerArcRectangle, 0, 360, false, mInnerArcPaint);

		if (mNumber == 0) {
			canvas.save();
			canvas.rotate(progressAnimateStartAngle, getOriginal().x,
					getOriginal().y);
			canvas.drawArc(mInnerArcRectangle, 0, 360, false, mInnerArcPaint);
			canvas.restore();
		} else {
			mInnerArcPaint.setColor(0xfff39700);
			canvas.drawArc(mInnerArcRectangle, -90, (360 * mNumber / 9000),
					false, mInnerArcPaint);
		}

		Rect rect = new Rect();
		mNumberTextPaint.getTextBounds(mNumber + "", 0,
				(mNumber + "").length(), rect);
		int txWidth = rect.width();
		int txHeight = rect.height();
		canvas.drawText(mNumber + "", getOriginal().x - txWidth / 2,
				getOriginal().y + txHeight / 2, mNumberTextPaint);
	}

	/**
	 * Drawing according to the index value sectors, the first sector is located
	 * in the center of three o 'clock direction
	 * 
	 **/
	public void drawArc(int index, Canvas canvas) {
		int startdegree = mPieceDegree * (index)
				- (mPieceDegree - mDividerDegree) / 2;
		if (index == mSelectIndex) {
			mOuterArcPaint.setColor(0xFFcacccc);
		} else {
			mOuterArcPaint.setColor(outArcColor[index]);
		}
		float radious = ((float) mWidth - (float) outArctrokeWidth) - padding;
		float midDegree = startdegree + (mPieceDegree - mDividerDegree);
		double x = radious * Math.cos(midDegree * Math.PI / 180);
		double y = radious * Math.sin(midDegree * Math.PI / 180);
		x = x + getOriginal().x;
		y = y + getOriginal().y;
		canvas.drawArc(mOuterArcRectangle, startdegree, mPieceDegree
				- mDividerDegree, false, mOuterArcPaint);
		Rect rect = new Rect();
		mOuterTextPaint.getTextBounds(items.get(index).getTitle(), 0, items
				.get(index).getTitle().length(), rect);
		int txWidth = rect.width();
		int txHeight = rect.height();
		canvas.drawText(items.get(index).getTitle(), (int) x - txWidth / 2,
				(int) y + txHeight / 2, mOuterTextPaint);
	}

	/** 根据触摸坐标获取扇区的索引 */
	public int getTouchArea(Point p) {
		int index = -2;
		float absdy = Math.abs(p.y - getOriginal().y);
		float absdx = Math.abs(p.x - getOriginal().x);
		if (absdx * absdx + absdy * absdy < ((float) mWidth / 2
				- outArctrokeWidth - overTouchDistance - padding)
				* ((float) mWidth / 2 - outArctrokeWidth - overTouchDistance - padding)) {
			return -1;
		}
		float absdy1 = Math.abs(p.y - getOriginal().y);
		float absdx1 = Math.abs(p.x - getOriginal().x);
		if (absdx * absdx + absdy * absdy < ((float) mWidth / 2
				- mInnerArctrokeWidth - overTouchDistance - padding)
				* ((float) mWidth / 2 - mInnerArctrokeWidth - overTouchDistance - padding)) {
			return -2;
		}
		double dx = Math.atan2(p.y - getOriginal().y, p.x - getOriginal().x);
		float fDegree = (float) (dx / (2 * Math.PI) * 360);
		fDegree = (fDegree + 360) % 360;
		int start = -(mPieceDegree - mDividerDegree) / 2;
		Log.i(TAG, "fDegree =" + fDegree);
		for (int i = 0; i < mPieceNumber; i++) {
			int end = start + mPieceDegree - mDividerDegree;
			if (start <= fDegree && fDegree <= 360) { //end
				index = i;
			}
			start = mPieceDegree * (i + 1) - (mPieceDegree - mDividerDegree)/ 2;
			
		}
		return index;
	}

	public Point getOriginal() {
		return new Point((float) mWidth / 2, (float) mWidth / 2);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int height = getDefaultSize(getSuggestedMinimumHeight(),
				heightMeasureSpec);
		int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
		int min = Math.min(width, height);
		mWidth = min;
		outArctrokeWidth = min / 4;
		mInnerArctrokeWidth = outArctrokeWidth / 3;
		mInnerArcPaint.setStrokeWidth(mInnerArctrokeWidth);
		mOuterTextPaint.setTextSize(outArctrokeWidth / 6);
		mOuterArcPaint.setStrokeWidth(outArctrokeWidth);

		mOuterArcRadius = mWidth - outArctrokeWidth / 2 - padding;
		mInnerArcRadius = mWidth / 6;

		mProgressPaint.setStrokeWidth(mInnerArctrokeWidth);
		mSweepGradient = new SweepGradient(getOriginal().x, getOriginal().y,
				0xfff39700, Color.WHITE);
		mProgressPaint.setShader(mSweepGradient);

		mOuterArcRectangle.set(outArctrokeWidth / 2 + padding, outArctrokeWidth
				/ 2 + padding, mOuterArcRadius, mOuterArcRadius);
		mInnerArcRectangle.set(mWidth / 2 - mInnerArcRadius, mWidth / 2
				- mInnerArcRadius, mWidth / 2 + mInnerArcRadius, mWidth / 2
				+ mInnerArcRadius);
		mInnerBackGroud.setBounds((int) outArctrokeWidth + padding,
				(int) outArctrokeWidth + padding,
				(int) (min - outArctrokeWidth - padding), (int) (min
						- outArctrokeWidth - padding));
		mOuterBackGroud.setBounds(0, 0, mWidth, mWidth);
		setMeasuredDimension(min, min);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			mSelectIndex = getTouchArea(new Point(event.getX(), event.getY()));
			this.invalidate();
			Log.i(TAG, "mSelectIndex =" + mSelectIndex);
			// mSelectIndex = -1;
		} else if (event.getAction() == MotionEvent.ACTION_UP
				&& event.getAction() != MotionEvent.ACTION_CANCEL) {
			int upIndex = getTouchArea(new Point(event.getX(), event.getY()));
			if (mListener != null) {
				mListener.onPieceClick(upIndex);
			}
			mSelectIndex = -2;
			this.invalidate();
		} else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
			mSelectIndex = -2;
			this.invalidate();
		}
		return true;
	}

	/** Set up the number in the circle */
	public void setNumber(int number) {
		mNumber = number;
		this.invalidate();
	}

	/** Get the number in the circle */
	public int getNumber() {
		return mNumber;
	}

	/** Set the animation starting value */
	public void setProgressAnimateStartAngle(int startAngle) {
		progressAnimateStartAngle = startAngle;
		this.invalidate();
	}

	public int getProgressAnimateStartAngle() {
		return progressAnimateStartAngle;
	}

	public void setItems(List<GossipItem> items1) {
		this.items = items1;
		mPieceNumber = items.size();
		mPieceDegree = 360 / mPieceNumber;
	}

	/** Set the click event */
	public void setOnPieceClickListener(OnPieceClickListener l) {
		mListener = l;
	}

}
