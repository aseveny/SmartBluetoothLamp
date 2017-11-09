package com.action.bluetooth;


import com.action.bluetooth.until.Bluetooth;
import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ColorSettings extends Activity {

	private SeekBar redSeekBar;
	private SeekBar greenSeekBar;
	private SeekBar blueSeekBar;
	private ActionBar backaActionBar;
	private ImageView actionbar_back;
	
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rgb_cct);
		init();
		initActionBar();
	}

	private void init() {
		preferences = this.getSharedPreferences("setting",
				MODE_PRIVATE);
		redSeekBar = (SeekBar) findViewById(R.id.seekbar_self_red);
		greenSeekBar = (SeekBar) findViewById(R.id.seekbar_self_green);
		blueSeekBar = (SeekBar) findViewById(R.id.seekbar_self_blue);
		actionbar_back = (ImageView) findViewById(R.id.actionbar_back);
		redSeekBar.setOnSeekBarChangeListener(new OnChangeListener());
		greenSeekBar.setOnSeekBarChangeListener(new OnChangeListener());
		blueSeekBar.setOnSeekBarChangeListener(new OnChangeListener());
		int redprogress = preferences.getInt("redseek", 80);
		int greenprogress = preferences.getInt("greenseek", 20);
		int blueprogress = preferences.getInt("blueseek", 80);
		this.redSeekBar.setProgress(redprogress);
		this.greenSeekBar.setProgress(greenprogress);
		this.blueSeekBar.setProgress(blueprogress);
		actionbar_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	class OnChangeListener implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub

			switch (seekBar.getId()) {
			case R.id.seekbar_self_red:

				redSeekBar = seekBar;
				onStarTrackTouchRed(redSeekBar, progress);		

				break;
			case R.id.seekbar_self_green:

				greenSeekBar = seekBar;
				onStarTrackTouchGreen(greenSeekBar, progress);
			

				break;
			case R.id.seekbar_self_blue:

				blueSeekBar = seekBar;
				onStarTrackTouchBlue(blueSeekBar, progress);
				progress = blueSeekBar.getProgress();

				break;

			default:
				break;

			}
			seekBar.setProgress(progress);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

			switch (seekBar.getId()) {
			case R.id.seekbar_self_red:

				redSeekBar = seekBar;
				//seekBar.setPressed(false);
				

				break;
			case R.id.seekbar_self_green:

				greenSeekBar = seekBar;

				break;
			case R.id.seekbar_self_blue:

				blueSeekBar = seekBar;

				break;

			default:
				break;
			}

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			switch (seekBar.getId()) {
			case R.id.seekbar_self_red:

				redSeekBar = seekBar;

				break;
			case R.id.seekbar_self_green:

				greenSeekBar = seekBar;

				break;
			case R.id.seekbar_self_blue:

				blueSeekBar = seekBar;

				break;

			default:
				break;
			}

		}

	}

	// set the progress's value

	private void onStarTrackTouchRed(SeekBar seekBar, int progress) {
		// TODO Auto-generated method stub
		if (progress <= 3.125f) {

			Bluetooth.sendMessage("AT#IR017BW", ColorSettings.this);

			Log.d("progress", "000");
		} else if (progress > 3.125f && progress <= 6.125f) {

			Bluetooth.sendMessage("AT#IR018BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 6.125f && progress <= 9.375f) {

			Bluetooth.sendMessage("AT#IR019BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 9.375f && progress <= 12.500f) {

			Bluetooth.sendMessage("AT#IR020BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 12.500f && progress <= 15.625f) {

			Bluetooth.sendMessage("AT#IR021BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 15.625f && progress <= 18.750f) {

			Bluetooth.sendMessage("AT#IR022BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 18.750 && progress <= 21.875f) {

			Bluetooth.sendMessage("AT#IR023BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 21.875f && progress <= 31.250f) {

			Bluetooth.sendMessage("AT#IR024BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 31.250f && progress <= 34.375f) {

			Bluetooth.sendMessage("AT#IR025BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 34.375f && progress <= 37.500f) {

			Bluetooth.sendMessage("AT#IR026BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 37.500f && progress <= 40.625f) {

			Bluetooth.sendMessage("AT#IR027BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 40.625f && progress <= 43.750f) {

			Bluetooth.sendMessage("AT#IR028BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 43.750f && progress <= 46.875f) {

			Bluetooth.sendMessage("AT#IR029BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 46.875f && progress <= 50.000f) {

			Bluetooth.sendMessage("AT#IR030BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 50.000f && progress <= 53.125f) {

			Bluetooth.sendMessage("AT#IR031BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 53.125f && progress <= 56.250f) {

			Bluetooth.sendMessage("AT#IR032BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 56.250f && progress <= 59.375f) {

			Bluetooth.sendMessage("AT#IR033BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 59.375f && progress <= 62.500f) {

			Bluetooth.sendMessage("AT#IR034BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 62.500f && progress <= 65.625f) {

			Bluetooth.sendMessage("AT#IR035BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 65.625f && progress <= 68.750f) {

			Bluetooth.sendMessage("AT#IR036BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 68.750f && progress <= 71.875f) {

			Bluetooth.sendMessage("AT#IR037BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 71.875f && progress <= 75.000f) {

			Bluetooth.sendMessage("AT#IR038BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 75.000f && progress <= 78.125f) {

			Bluetooth.sendMessage("AT#IR039BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 78.125f && progress <= 81.250f) {

			Bluetooth.sendMessage("AT#IR040BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 81.250f && progress <= 84.375f) {

			Bluetooth.sendMessage("AT#IR041BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 81.250f && progress <= 84.375f) {

			Bluetooth.sendMessage("AT#IR042BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 84.375f && progress <= 87.500f) {

			Bluetooth.sendMessage("AT#IR043BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 87.500f && progress <= 90.625f) {

			Bluetooth.sendMessage("AT#IR044BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 90.625f && progress < 93.750f) {

			Bluetooth.sendMessage("AT#IR045BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 93.750f && progress <= 96.875f) {

			Bluetooth.sendMessage("AT#IR046BW", ColorSettings.this);
			Log.d("progress", "100");

		} else if (progress > 96.875f && progress < 10.000f) {

			Bluetooth.sendMessage("AT#IR047BW", ColorSettings.this);
			Log.d("progress", "100");

		} else if (progress >= 100.000f) {

			Bluetooth.sendMessage("AT#IR048BW", ColorSettings.this);
			Log.d("progress", "100");

		}
		preferences = this.getSharedPreferences("setting",
				MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt("redseek", progress);
		editor.commit();

	}

	private void onStarTrackTouchGreen(SeekBar seekBar, int progress) {
		// TODO Auto-generated method stub
		if (progress <= 3.125f) {

			Bluetooth.sendMessage("AT#IR049BW", ColorSettings.this);

			Log.d("progress", "000");
		} else if (progress > 3.125f && progress <= 6.125f) {

			Bluetooth.sendMessage("AT#IR050BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 6.125f && progress <= 9.375f) {

			Bluetooth.sendMessage("AT#IR051BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 9.375f && progress <= 12.500f) {

			Bluetooth.sendMessage("AT#IR052BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 12.500f && progress <= 15.625f) {

			Bluetooth.sendMessage("AT#IR053BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 15.625f && progress <= 18.750f) {

			Bluetooth.sendMessage("AT#IR054BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 18.750 && progress <= 21.875f) {

			Bluetooth.sendMessage("AT#IR055BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 21.875f && progress <= 31.250f) {

			Bluetooth.sendMessage("AT#IR056BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 31.250f && progress <= 34.375f) {

			Bluetooth.sendMessage("AT#IR057BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 34.375f && progress <= 37.500f) {

			Bluetooth.sendMessage("AT#IR058BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 37.500f && progress <= 40.625f) {

			Bluetooth.sendMessage("AT#IR059BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 40.625f && progress <= 43.750f) {

			Bluetooth.sendMessage("AT#IR060BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 43.750f && progress <= 46.875f) {

			Bluetooth.sendMessage("AT#IR061BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 46.875f && progress <= 50.000f) {

			Bluetooth.sendMessage("AT#IR062BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 50.000f && progress <= 53.125f) {

			Bluetooth.sendMessage("AT#IR063BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 53.125f && progress <= 56.250f) {

			Bluetooth.sendMessage("AT#IR064BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 56.250f && progress <= 59.375f) {

			Bluetooth.sendMessage("AT#IR065BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 59.375f && progress <= 62.500f) {

			Bluetooth.sendMessage("AT#IR066BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 62.500f && progress <= 65.625f) {

			Bluetooth.sendMessage("AT#IR067BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 65.625f && progress <= 68.750f) {

			Bluetooth.sendMessage("AT#IR068BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 68.750f && progress <= 71.875f) {

			Bluetooth.sendMessage("AT#IR069BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 71.875f && progress <= 75.000f) {

			Bluetooth.sendMessage("AT#IR070BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 75.000f && progress <= 78.125f) {

			Bluetooth.sendMessage("AT#IR071BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 78.125f && progress <= 81.250f) {

			Bluetooth.sendMessage("AT#IR072BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 81.250f && progress <= 84.375f) {

			Bluetooth.sendMessage("AT#IR073BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 81.250f && progress <= 84.375f) {

			Bluetooth.sendMessage("AT#IR074BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 84.375f && progress <= 87.500f) {

			Bluetooth.sendMessage("AT#IR075BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 87.500f && progress <= 90.625f) {

			Bluetooth.sendMessage("AT#IR076BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 90.625f && progress < 93.750f) {

			Bluetooth.sendMessage("AT#IR077BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 93.750f && progress <= 96.875f) {

			Bluetooth.sendMessage("AT#IR078BW", ColorSettings.this);
			Log.d("progress", "100");

		} else if (progress > 96.875f && progress < 10.000f) {

			Bluetooth.sendMessage("AT#IR079BW", ColorSettings.this);
			Log.d("progress", "100");

		} else if (progress >= 100.000f) {

			Bluetooth.sendMessage("AT#IR080BW", ColorSettings.this);
			Log.d("progress", "100");

		}
		Editor editor = preferences.edit();
		editor.putInt("greenseek", progress);
		editor.commit();
	}

	private void onStarTrackTouchBlue(SeekBar seekBar, int progress) {
		// TODO Auto-generated method stub
		if (progress <= 3.125f) {

			Bluetooth.sendMessage("AT#IR081BW", ColorSettings.this);

			Log.d("progress", "000");
		} else if (progress > 3.125f && progress <= 6.125f) {

			Bluetooth.sendMessage("AT#IR082BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 6.125f && progress <= 9.375f) {

			Bluetooth.sendMessage("AT#IR083BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 9.375f && progress <= 12.500f) {

			Bluetooth.sendMessage("AT#IR084BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 12.500f && progress <= 15.625f) {

			Bluetooth.sendMessage("AT#IR085BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 15.625f && progress <= 18.750f) {

			Bluetooth.sendMessage("AT#IR086BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 18.750 && progress <= 21.875f) {

			Bluetooth.sendMessage("AT#IR087BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 21.875f && progress <= 31.250f) {

			Bluetooth.sendMessage("AT#IR088BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 31.250f && progress <= 34.375f) {

			Bluetooth.sendMessage("AT#IR089BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 34.375f && progress <= 37.500f) {

			Bluetooth.sendMessage("AT#IR090BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 37.500f && progress <= 40.625f) {

			Bluetooth.sendMessage("AT#IR091BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 40.625f && progress <= 43.750f) {

			Bluetooth.sendMessage("AT#IR092BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 43.750f && progress <= 46.875f) {

			Bluetooth.sendMessage("AT#IR093BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 46.875f && progress <= 50.000f) {

			Bluetooth.sendMessage("AT#IR094BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 50.000f && progress <= 53.125f) {

			Bluetooth.sendMessage("AT#IR095BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 53.125f && progress <= 56.250f) {

			Bluetooth.sendMessage("AT#IR096BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 56.250f && progress <= 59.375f) {

			Bluetooth.sendMessage("AT#IR097BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 59.375f && progress <= 62.500f) {

			Bluetooth.sendMessage("AT#IR098BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 62.500f && progress <= 65.625f) {

			Bluetooth.sendMessage("AT#IR099BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 65.625f && progress <= 68.750f) {

			Bluetooth.sendMessage("AT#IR100BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 68.750f && progress <= 71.875f) {

			Bluetooth.sendMessage("AT#IR101BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 71.875f && progress <= 75.000f) {

			Bluetooth.sendMessage("AT#IR102BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 75.000f && progress <= 78.125f) {

			Bluetooth.sendMessage("AT#IR103BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 78.125f && progress <= 81.250f) {

			Bluetooth.sendMessage("AT#IR104BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 81.250f && progress <= 84.375f) {

			Bluetooth.sendMessage("AT#IR105BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 81.250f && progress <= 84.375f) {

			Bluetooth.sendMessage("AT#IR106BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 84.375f && progress <= 87.500f) {

			Bluetooth.sendMessage("AT#IR107BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		}

		else if (progress > 87.500f && progress <= 90.625f) {

			Bluetooth.sendMessage("AT#IR108BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 90.625f && progress < 93.750f) {

			Bluetooth.sendMessage("AT#IR109BW", ColorSettings.this);
			Log.d("progress", "progress=" + progress);
		} else if (progress > 93.750f && progress <= 96.875f) {

			Bluetooth.sendMessage("AT#IR110BW", ColorSettings.this);
			Log.d("progress", "100");

		} else if (progress > 96.875f && progress < 10.000f) {

			Bluetooth.sendMessage("AT#IR111BW", ColorSettings.this);
			Log.d("progress", "100");

		} else if (progress >= 100.000f) {

			Bluetooth.sendMessage("AT#IR112BW", ColorSettings.this);
			Log.d("progress", "100");

		}

		Editor editor = preferences.edit();
		editor.putInt("blueseek", progress);
		editor.commit();
	}

	private void initActionBar() {

		backaActionBar = getActionBar();
		if (backaActionBar != null) {
			// icon click
			Resources res = getResources();
			Drawable myDrawable = res.getDrawable(R.drawable.theme_bg);
			// actionBar.setStackedBackgroundDrawable(myDrawable);
			// backaActionBar.setSplitBackgroundDrawable(myDrawable);
			backaActionBar.setBackgroundDrawable(myDrawable);

			backaActionBar.setTitle("SAYAO LIGHTING");
			backaActionBar.setLogo(myDrawable);
			backaActionBar.setDisplayHomeAsUpEnabled(true);
			backaActionBar.setDisplayShowHomeEnabled(true);
			Drawable themeBack = res.getDrawable(R.drawable.theme_bg);
			//backaActionBar.setHomeAsUpIndicator(themeBack);

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
