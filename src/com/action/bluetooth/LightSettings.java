package com.action.bluetooth;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.action.bluetooth.adpter.RadioButtonAdapter;
import com.action.bluetooth.adpter.RadioButtonAdapter.CheckStateUpdateListener;
import com.action.bluetooth.until.Bluetooth;

public class LightSettings extends Activity {
	// save the state
	private List<Boolean> scenariochoiceState = new ArrayList<Boolean>();
	private List<Boolean> lampchoiceState = new ArrayList<Boolean>();
	private List<String> scenariodata;
	private List<String> lampdata;
	private RadioButtonAdapter scenarioadapter;
	private RadioButtonAdapter lampadapters;
	private int positionCheck = -1;// checked id
	private int positionChecklamp = -1;
	private String scenarioitem[] = { "聚合", "舒适", "睡眠", "工作" };
	private String lampitem[] = { "色调", "流水", "追逐" };

	private ListView scenario;
	private ListView lamp;
	private ActionBar backaActionBar;
	private ImageView actionbar_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light_set);
		scenario = (ListView) findViewById(R.id.lv);
		lamp = (ListView) findViewById(R.id.lv2);
		actionbar_back=(ImageView) findViewById(R.id.actionbar_back);
		scenarioInit();
		lampInit();
		initActionBar();
	}

	protected void scenarioInit() {

		scenariodata = new ArrayList<String>();
		for (int i = 0; i <= 3; i++) {
			scenariodata.add(scenarioitem[i]);
			scenariochoiceState.add(false);// init the null

		}

		scenarioadapter = new RadioButtonAdapter(this, scenariodata);
		scenarioadapter.setCheckStates(scenariochoiceState);
		scenarioadapter
				.setCheckStateUpdateListener(new CheckStateUpdateListener() {
					@Override
					public void checkStateUpdate(int position) {
						positionCheck = position;
						switch (position) {
						case 0:
							Bluetooth.sendMessage("AT#IR161BW",
									LightSettings.this);
							Log.d("AT", "AT#IR161BW");
							break;
						case 1:
							Bluetooth.sendMessage("AT#IR162BW",
									LightSettings.this);
							break;
						case 2:
							Bluetooth.sendMessage("AT#IR163BW",
									LightSettings.this);
							break;
						case 3:
							Bluetooth.sendMessage("AT#IR164BW",
									LightSettings.this);
							break;

						default:
							break;
						}
						scenariochoiceState.set(position, true);
						scenarioadapter.setCheckStates(scenariochoiceState);
						scenarioadapter.notifyDataSetChanged();
					}
				});

		scenario.setAdapter(scenarioadapter);
		scenario.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// set the one is checked

				// checked?

				if (positionCheck != -1) {
					scenariochoiceState.set(positionCheck, false);
				}
				// set the one is checked
				positionCheck = position;
				// update the datas
				switch (position) {
				case 0:
					Bluetooth.sendMessage("AT#IR161BW", LightSettings.this);
					Log.d("AT", "AT#IR161BW");
					scenariochoiceState.set(position, true);
					break;
				case 1:
					Bluetooth.sendMessage("AT#IR162BW", LightSettings.this);
					scenariochoiceState.set(position, true);
					break;
				case 2:
					Bluetooth.sendMessage("AT#IR163BW", LightSettings.this);
					scenariochoiceState.set(position, true);
					break;
				case 3:
					Bluetooth.sendMessage("AT#IR164BW", LightSettings.this);
					scenariochoiceState.set(position, true);
					break;

				default:
					break;
				}

				scenarioadapter.setCheckStates(scenariochoiceState);
				scenarioadapter.notifyDataSetChanged();

			}
		});
	}

	protected void lampInit() {

		lampdata = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			lampdata.add(lampitem[i]);
			lampchoiceState.add(false);//

		}

		lampadapters = new RadioButtonAdapter(this, lampdata);
		lampadapters.setCheckStates(lampchoiceState);
		lampadapters
				.setCheckStateUpdateListener(new CheckStateUpdateListener() {
					@Override
					public void checkStateUpdate(int position) {
						positionChecklamp = position;
						switch (position) {
						case 0:
							Bluetooth.sendMessage("AT#IR166BW",
									LightSettings.this);
							break;
						case 1:
							Bluetooth.sendMessage("AT#IR167BW",
									LightSettings.this);
							break;
						case 2:
							Bluetooth.sendMessage("AT#IR168BW",
									LightSettings.this);
							break;

						default:
							break;
						}
					}
				});

		lamp.setAdapter(lampadapters);
		lamp.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				lampchoiceState.set(position, true);

				if (positionChecklamp != -1) {
					lampchoiceState.set(positionChecklamp, false);
				}

				positionChecklamp = position;
				switch (position) {
				case 0:
					Bluetooth.sendMessage("AT#IR166BW", LightSettings.this);

					break;
				case 1:
					Bluetooth.sendMessage("AT#IR167BW", LightSettings.this);

					break;
				case 2:
					Bluetooth.sendMessage("AT#IR168BW", LightSettings.this);

					break;

				default:
					break;
				}
				lampchoiceState.set(position, true);
				lampadapters.setCheckStates(lampchoiceState);
				lampadapters.notifyDataSetChanged();
			}
		});

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
			backaActionBar.setDisplayShowHomeEnabled(false);
			
			// backaActionBar.setDisplayShowTitleEnabled(true);

		}
		
		actionbar_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

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
