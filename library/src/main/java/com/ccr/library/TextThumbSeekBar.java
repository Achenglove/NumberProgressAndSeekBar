package com.ccr.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * @Created on 2018/8/29.
 * @autthor Acheng
 * @Email 345887272@qq.com
 * @Description
 */

public class TextThumbSeekBar extends android.support.v7.widget.AppCompatSeekBar {
    private int mThumbSize;//绘制滑块宽度
    private TextPaint mTextPaint;//绘制文本的大小
    private int mSeekBarMin=0;//滑块开始值
    private String mProgressText;

    public TextThumbSeekBar(Context context) {
        this(context, null);
    }

    public TextThumbSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.seekBarStyle);
    }

    public TextThumbSeekBar(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
        mThumbSize=getResources().getDimensionPixelSize(R.dimen.width25);
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.txtSize16));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int unsignedMin = mSeekBarMin < 0 ? mSeekBarMin * -1 : mSeekBarMin;
        String progressText = mProgressText;//String.valueOf(getProgress()+unsignedMin);
        if(TextUtils.isEmpty(progressText)){
            progressText= String.valueOf(getProgress()+unsignedMin);
        }
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(progressText, 0, progressText.length(), bounds);

        int leftPadding = getPaddingLeft() - getThumbOffset();
        int rightPadding = getPaddingRight() - getThumbOffset();
        int width = getWidth() - leftPadding - rightPadding;
        float progressRatio = (float) getProgress() / getMax();
        float thumbOffset = mThumbSize * (.5f - progressRatio);
        float thumbX = progressRatio * width + leftPadding + thumbOffset;
        float thumbY = getHeight() / 2f + bounds.height() / 2f;
        canvas.drawText(progressText, thumbX, thumbY, mTextPaint);
    }

    public void setMix(int min){
        mSeekBarMin=min;
    }
    public void setText(String text){
        mProgressText=text;
    }
}
