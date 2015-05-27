package com.example.messagebubble;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class MessageBubble extends View {
	private boolean DEBUG = false;
	private Paint p;
	private Paint bgPaint;
	private int max = 99;
	private int progress = 0;
	private Rect r = new Rect();
	private static final String PRE_MEASURE_STRING = "88";
	
	private static final int[] attr = {
		android.R.attr.textSize,
		android.R.attr.textColor,
		android.R.attr.background,
		android.R.attr.progress,
	};

	public MessageBubble(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	public MessageBubble(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MessageBubble(Context context) {
		this(context, null);
	}
	
	private void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, attr);
		float textSize = a.getDimension(0, 10);
		int textColor = a.getColor(1, Color.WHITE);
		int bgColor = a.getColor(2, Color.RED);
		progress = a.getInteger(3, 0);
		a.recycle();
		
		p = new Paint();
		p.setAntiAlias(true);
		p.setColor(textColor);
		p.setTextSize(textSize);
		p.setStyle(Style.FILL);
		p.setTextAlign(Align.CENTER);
		
		//pre-measure
		p.getTextBounds(PRE_MEASURE_STRING, 0, PRE_MEASURE_STRING.length(), r);
		
		bgPaint = new Paint();
		bgPaint.setAntiAlias(true);
		bgPaint.setStyle(Style.FILL);
		bgPaint.setColor(bgColor);
	}
	
	@Override
	public void setBackgroundColor(int color) {
		bgPaint.setColor(color);
		invalidate();
	}
	
	public void setTextColor(int color) {
		p.setColor(color);
		invalidate();
	}
	
	public void setTextSize(float sp) {
		p.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getContext().getResources().getDisplayMetrics()));
		p.getTextBounds(PRE_MEASURE_STRING, 0, PRE_MEASURE_STRING.length(), r);
		requestLayout();
		invalidate();
	}
	
	public void setProgress(int progress) {
		if (progress < 0) progress = 0;
		if (this.progress == 0 || progress == 0) {
			requestLayout();
		} 
		this.progress = progress;
		invalidate();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!DEBUG && progress <= 0) {
			setMeasuredDimension(0, 0);
		} else {
			int w = getPadding() + Math.max(r.width(), r.height());
			int diameter = (int) (Math.sqrt(w * w * 2)  + .5f);
			setMeasuredDimension(diameter, diameter);
		}
	}
	
	private int getPadding() {
		return Math.max(Math.max(Math.max(getPaddingLeft(), getPaddingRight()), getPaddingTop()), getPaddingBottom());
	}

	@Override
	public void draw(Canvas canvas) {
		if (!DEBUG && progress <= 0) return;
		float radius = getWidth() / 2f;
		canvas.drawCircle(radius, radius, radius, bgPaint);
		
		if (progress > max) {
			float r = p.getTextSize() / 8;
			canvas.drawCircle(radius, radius, r, p);
			canvas.drawCircle(radius - r * 2.5f, radius, r, p);
			canvas.drawCircle(radius + r * 2.5f, radius, r, p);
		} else {
			//微调出来的位置
			canvas.drawText(Integer.toString(progress), radius - 0.5f, radius + r.height() / 2f - 1.5f, p);
		}
	}
}
