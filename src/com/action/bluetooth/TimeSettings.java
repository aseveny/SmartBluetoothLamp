package com.action.bluetooth;

import com.action.bluetooth.until.Bluetooth;
import com.action.bluetooth.view.SlipButton;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimeSettings extends Activity {
	private TimePicker mTurnOnLightTimePicker;
	private TimePicker mTurnOffLightTimePicker;
	private String zifu1 = "AT#AO";
	private String zifu2 = "BW";
	private String TurnOnzifu = null;
	private String TurnOffzifu = null;
	private int mTurnOnLightHour;
	private int mTurnOnLightMin;
	private int mTurnOffLightHour;
	private int mTurnOffLightMin;
	private SlipButton switchOn;
	private SlipButton switchOff;

	private ActionBar backaActionBar;
	private ImageView actionbar_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_time);
		TimeSetInit();
		Switchonoff();
		initActionBar();
	}

	private void TimeSetInit() {
		mTurnOnLightTimePicker = (TimePicker) findViewById(R.id.turnontimePicker);
		mTurnOnLightTimePicker.setIs24HourView(true);

		mTurnOffLightTimePicker = (TimePicker) findViewById(R.id.turnofftimePicker);
		mTurnOffLightTimePicker.setIs24HourView(true);

		actionbar_back=(ImageView) findViewById(R.id.actionbar_back);
		actionbar_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	private void Switchonoff() {
		switchOn = (SlipButton) findViewById(R.id.time_turnon);
		switchOn.setCheck(true);
		switchOn.SetOnChangedListener(new SlipButton.OnChangedListener() {

			public void OnChanged(boolean CheckState) {
				// TODO Auto-generated method stub
				mTurnOnLightHour = mTurnOnLightTimePicker.getCurrentHour();
				mTurnOnLightMin = mTurnOnLightTimePicker.getCurrentMinute();
				TurnOnzifu = zifu1 + mTurnOnLightHour + mTurnOnLightMin + zifu2;
				if (CheckState) {

					Bluetooth.sendMessage(TurnOnzifu, TimeSettings.this);
				} else {
					// Bluetooth.sendMessage("AT#IR128BW",TimeSettings.this);
				}
			}

		});

		switchOff = (SlipButton) findViewById(R.id.time_turnoff);
		switchOff.SetOnChangedListener(new SlipButton.OnChangedListener() {

			public void OnChanged(boolean CheckState) {
				// TODO Auto-generated method stub
				mTurnOffLightHour = mTurnOffLightTimePicker.getCurrentHour();

				mTurnOffLightMin = mTurnOffLightTimePicker.getCurrentMinute();

				TurnOffzifu = zifu1 + mTurnOffLightHour + mTurnOffLightMin
						+ zifu2;
				if (CheckState) {

					Bluetooth.sendMessage(TurnOffzifu, TimeSettings.this);
				} else {
					// Bluetooth.sendMessage("AT#IR128BW",TimeSettings.this);
				}
			}

		});
	}

	private void initActionBar() {

		backaActionBar = getActionBar();
		if (backaActionBar != null) {
			// icon click
			Resources res = getResources();
			Drawable myDrawable = res.getDrawable(R.drawable.theme_bg);
			//backaActionBar.setStackedBackgroundDrawable(myDrawable);
			// backaActionBar.setSplitBackgroundDrawable(myDrawable);
			backaActionBar.setBackgroundDrawable(myDrawable);
			backaActionBar.setTitle("SAYAO LIGHTING");
			backaActionBar.setLogo(myDrawable);
			backaActionBar.setDisplayHomeAsUpEnabled(true);
			backaActionBar.setDisplayShowHomeEnabled(false);
			
			

		}
		

	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
