package com.action.bluetooth;

import java.util.ArrayList;
import java.util.List;

import com.action.bluetooth.adpter.RadioButtonAdapter;
import com.action.bluetooth.adpter.RadioButtonAdapter.CheckStateUpdateListener;
import com.action.bluetooth.until.Bluetooth;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class DelayTimeSettings extends Activity {

	private List<Boolean> choiceState = new ArrayList<Boolean>();
	private List<String> data;
	private RadioButtonAdapter rbtuttonAdapter;
	private int positionCheck = -1;// checked id
	private String scenarioitem[] = { "5五分钟延时", "10五分钟延时", "20五分钟延时",
			"30五分钟延时", "40五分钟延时" };
	private ListView delayListView;

	private ActionBar backaActionBar;
	private ImageView actionbar_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_time_delay);
		// mMainActivity = new MainActivity();
		delayListView = (ListView) findViewById(R.id.delay_time_listview);
		actionbar_back = (ImageView) findViewById(R.id.actionbar_back);
		initActionBar();
		delayInit();

	}

	protected void delayInit() {

		data = new ArrayList<String>();
		for (int i = 0; i <= 3; i++) {
			data.add(scenarioitem[i]);
			choiceState.add(false);// init the null

		}

		rbtuttonAdapter = new RadioButtonAdapter(this, data);
		rbtuttonAdapter.setCheckStates(choiceState);
		rbtuttonAdapter
				.setCheckStateUpdateListener(new CheckStateUpdateListener() {
					@Override
					public void checkStateUpdate(int position) {
						positionCheck = position;
						switch (position) {
						case 0:
							Bluetooth.sendMessage("AT#AS05BW",
									DelayTimeSettings.this);

							break;
						case 1:
							Bluetooth.sendMessage("AT#AS10BW",
									DelayTimeSettings.this);
							break;
						case 2:
							Bluetooth.sendMessage("AT#AS20BW",
									DelayTimeSettings.this);
							break;
						case 3:
							Bluetooth.sendMessage("AT#AS30BW",
									DelayTimeSettings.this);
							break;

						case 4:
							Bluetooth.sendMessage("AT#AS40BW",
									DelayTimeSettings.this);
							break;

						default:
							break;
						}
						choiceState.set(position, true);
						rbtuttonAdapter.setCheckStates(choiceState);
						rbtuttonAdapter.notifyDataSetChanged();
					}
				});

		delayListView.setAdapter(rbtuttonAdapter);
		delayListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// set the one is checked

				// checked?

				if (positionCheck != -1) {
					choiceState.set(positionCheck, false);
				}
				// set the one is checked
				positionCheck = position;
				// update the datas
				switch (position) {
				case 0:
					Bluetooth.sendMessage("AT#AS05BW", DelayTimeSettings.this);

					break;
				case 1:
					Bluetooth.sendMessage("AT#AS10BW", DelayTimeSettings.this);
					break;
				case 2:
					Bluetooth.sendMessage("AT#AS20BW", DelayTimeSettings.this);
					break;
				case 3:
					Bluetooth.sendMessage("AT#AS30BW", DelayTimeSettings.this);
					break;

				case 4:
					Bluetooth.sendMessage("AT#AS40BW", DelayTimeSettings.this);
					break;

				default:
					break;
				}
				choiceState.set(position, true);
				rbtuttonAdapter.setCheckStates(choiceState);
				rbtuttonAdapter.notifyDataSetChanged();

			}
		});
	}

	private void initActionBar() {

		backaActionBar = getActionBar();
		if (backaActionBar != null) {
			// icon点击使能
			Resources res = getResources();
			Drawable myDrawable = res.getDrawable(R.drawable.theme_bg);
			backaActionBar.setBackgroundDrawable(myDrawable);
			backaActionBar.setTitle("SAYAO LIGHTING");

			backaActionBar.setDisplayHomeAsUpEnabled(true);

			backaActionBar.setDisplayShowHomeEnabled(false);

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
