package com.example.messagebubble;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnSeekBarChangeListener, OnClickListener {
	SeekBar num, textSize, padding;
	MessageBubble bb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		num = (SeekBar) findViewById(R.id.numSeekBar);
		textSize = (SeekBar) findViewById(R.id.textSizeSeekBar);
		padding = (SeekBar) findViewById(R.id.paddingSeekBar);
		
		num.setOnSeekBarChangeListener(this);
		textSize.setOnSeekBarChangeListener(this);
		padding.setOnSeekBarChangeListener(this);
		
		findViewById(R.id.bgColorWhite).setOnClickListener(this);
		findViewById(R.id.bgColorRed).setOnClickListener(this);
		findViewById(R.id.bgColorYellow).setOnClickListener(this);
		findViewById(R.id.bgColorBlue).setOnClickListener(this);
		
		findViewById(R.id.textColorWhite).setOnClickListener(this);
		findViewById(R.id.textColorRed).setOnClickListener(this);
		findViewById(R.id.textColorYellow).setOnClickListener(this);
		findViewById(R.id.textColorBlue).setOnClickListener(this);
		
		bb = (MessageBubble) findViewById(R.id.bb);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		switch (seekBar.getId()) {
		case R.id.numSeekBar:
			bb.setProgress(progress);
			break;
		case R.id.textSizeSeekBar:
			bb.setTextSize(8 + progress);
			break;
		case R.id.paddingSeekBar:
			bb.setPadding(progress, progress, progress, progress);
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bgColorWhite:
			bb.setBackgroundColor(Color.WHITE);
			break;
		case R.id.bgColorRed:
			bb.setBackgroundColor(Color.RED);
			break;
		case R.id.bgColorYellow:
			bb.setBackgroundColor(Color.YELLOW);
			break;
		case R.id.bgColorBlue:
			bb.setBackgroundColor(Color.BLUE);
			break;
		case R.id.textColorWhite:
			bb.setTextColor(Color.WHITE);
			break;
		case R.id.textColorRed:
			bb.setTextColor(Color.RED);
			break;
		case R.id.textColorYellow:
			bb.setTextColor(Color.YELLOW);
			break;
		case R.id.textColorBlue:
			bb.setTextColor(Color.BLUE);
			break;
		}
	}
}
