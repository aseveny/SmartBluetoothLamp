package com.action.bluetooth;

import java.util.ArrayList;
import java.util.List;
import com.action.bluetooth.chat.BluetoothChatService;
import com.action.bluetooth.chat.DeviceListActivity;
import com.action.bluetooth.chat.SendHandler;
import com.action.bluetooth.until.Bluetooth;
import com.action.bluetooth.until.GossipItem;
import com.action.bluetooth.until.IntenActions;
import com.action.bluetooth.view.BluePanel;
import com.action.bluetooth.view.Brightnes.OnSetChangedListener;
import com.action.bluetooth.view.Brightnes;
import com.action.bluetooth.view.GossipView;
import com.action.bluetooth.view.SlipButton;

import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler.Value;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	// Layout Views
	private TextView mTitle;

	// String buffer for outgoing messages
	private StringBuffer mOutStringBuffer;
	// Local Bluetooth adapter
	private BluetoothAdapter mBluetoothAdapter = null;
	// Member object for the chat services
	// private BluetoothChatService mChatService = null;

	// private IntenActions inActions;
	private String DEBUG = "Put color index";
	private GossipView gossipView;
	private SlipButton switchOn;
	private Button setColor;
	private Button lightONOFF;
	private Button setTime;
	private Button timeDelay;

	private SeekBar brightnesSeekBar;
	private float mCurrent;
	
	private ActionBar backaActionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initPieceItem();
		initSlipButton();
		initButton();
		initActionBar();

		mTitle = (TextView) findViewById(R.id.title_left_text);
		// mTitle.setText(R.string.app_name);
		mTitle = (TextView) findViewById(R.id.title_right_text);

		// Get local Bluetooth adapter
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		// If the adapter is null, then Bluetooth is not supported
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, "Bluetooth is not available",
					Toast.LENGTH_LONG).show();
			finish();
			return;
		}

	}

	@Override
	public void onStart() {
		super.onStart();
		if (Bluetooth.deBug())
			Log.e(Bluetooth.DEVICE_NAME, "++ ON START ++");

		// If BT is not on, request that it be enabled.
		// setupChat() will then be called during onActivityResult
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, Bluetooth.REQUEST_ENABLE_BT);
			// Otherwise, setup the chat session
		} else {
			if (Bluetooth.mChatService == null) {
				setupChat();
			}
		}
	}

	@Override
	public synchronized void onResume() {
		super.onResume();
		if (Bluetooth.deBug())
			Log.e(Bluetooth.DEVICE_NAME, "+ ON RESUME +");

		// Performing this check in onResume() covers the case in which BT was
		// not enabled during onStart(), so we were paused to enable it...
		// onResume() will be called when ACTION_REQUEST_ENABLE activity
		// returns.
		if (Bluetooth.mChatService != null) {
			// Only if the state is STATE_NONE, do we know that we haven't
			// started already
			if (Bluetooth.mChatService.getState() == BluetoothChatService.STATE_NONE) {
				// Start the Bluetooth chat services
				Bluetooth.mChatService.start();
			}
		}
	}

	private void initPieceItem() {

		gossipView = (GossipView) findViewById(R.id.gossipview);
		String[] strs = { "", "", "", "", "", "", "", "", "", "", "", "" };

		final List<GossipItem> items = new ArrayList<GossipItem>();
		for (int i = 0; i < strs.length; i++) {
			GossipItem item = new GossipItem(strs[i], 3);
			items.add(item);
		}
		gossipView.setItems(items);

		gossipView
				.setOnPieceClickListener(new GossipView.OnPieceClickListener() {

					public void onPieceClick(int index) {
						// TODO Auto-generated method stub
						// gossipView.setNumber(index);
						if (index != -1 && index != -2) {
							Log.d(DEBUG, "Color:" + items.get(index).getTitle());
							switch (index) {
							case 0:
								Bluetooth.sendMessage("AT#IR113BW",
										MainActivity.this);
								break;
							case 1:
								Bluetooth.sendMessage("AT#IR114BW",
										MainActivity.this);
								break;
							case 2:
								Bluetooth.sendMessage("AT#IR115BW",
										MainActivity.this);
								break;
							case 3:
								Bluetooth.sendMessage("AT#IR116BW",
										MainActivity.this);
								break;
							case 4:
								Bluetooth.sendMessage("AT#IR117BW",
										MainActivity.this);
								break;
							case 5:
								Bluetooth.sendMessage("AT#IR118BW",
										MainActivity.this);
								break;
							case 6:
								Bluetooth.sendMessage("AT#IR119BW",
										MainActivity.this);
								break;
							case 7:
								Bluetooth.sendMessage("AT#IR120BW",
										MainActivity.this);
								break;
							case 8:
								Bluetooth.sendMessage("AT#IR121BW",
										MainActivity.this);
								break;
							case 9:
								Bluetooth.sendMessage("AT#IR122BW",
										MainActivity.this);
								break;
							case 10:
								Bluetooth.sendMessage("AT#IR123BW",
										MainActivity.this);
								break;
							case 11:
								Bluetooth.sendMessage("AT#IR124BW",
										MainActivity.this);
								break;

							default:
								break;
							}
						}

						if (index == -1) {
							Bluetooth.sendMessage("AT#IR125BW",
									MainActivity.this);

						}
						if (index == -2) {
							Bluetooth.sendMessage("AT#IR126BW",
									MainActivity.this);
						}

					}

				});

	}

	private void initSlipButton() {

		switchOn = (SlipButton) findViewById(R.id.splitbutton);
		switchOn.SetOnChangedListener(new SlipButton.OnChangedListener() {

			public void OnChanged(boolean CheckState) {
				// TODO Auto-generated method stub
				if (CheckState) {
					Toast.makeText(MainActivity.this, "开", 200).show();
				} else {

				}
			}

		});
	}

	private void initButton() {
		setColor = (Button) findViewById(R.id.color);
		lightONOFF = (Button) findViewById(R.id.light);
		setTime = (Button) findViewById(R.id.timer);
		timeDelay = (Button) findViewById(R.id.delay_timer);
		brightnesSeekBar = (SeekBar) findViewById(R.id.seekbar_self_brightness);
		setColor.setOnClickListener(new PieceClick());
		lightONOFF.setOnClickListener(new PieceClick());
		setTime.setOnClickListener(new PieceClick());
		timeDelay.setOnClickListener(new PieceClick());
		brightnesSeekBar.setOnSeekBarChangeListener(new OnChangedListener());

		// brightnesSeekBar.setProgress((int) mCurrent);

	}

	class PieceClick implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			// TODO Auto-generated method stub
			// inActions=new IntenActions();
			switch (v.getId()) {
			case R.id.color:
				Log.d("aaaaaaaaaaa", "bbbbbbbbbbbb");
				IntenActions.getItemIntent(MainActivity.this,
						IntenActions.ACTIONS_COLOR);

				break;
			case R.id.light:
				IntenActions.getItemIntent(MainActivity.this,
						IntenActions.ACTIONS_LIGHT);
				break;
			case R.id.timer:
				IntenActions.getItemIntent(MainActivity.this,
						IntenActions.ACTIONS_TIME);
				break;
			case R.id.delay_timer:
				IntenActions.getItemIntent(MainActivity.this,
						IntenActions.ACTIONS_DELAY);
				break;

			default:
				break;
			}
		}

	}

	class OnChangedListener implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			onStarTrackTouch(seekBar, progress);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

	}

	private void onStarTrackTouch(SeekBar seekBar, int seekProgress) {
		// TODO Auto-generated method stub
		seekProgress = brightnesSeekBar.getProgress();
		mCurrent = seekProgress;

		if (mCurrent <= 6.25f) {
			// //brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR001BW", MainActivity.this);

			Log.d("progress", "000");
		} else if (mCurrent > 6.25f && mCurrent <= 12.500f) {

			// //brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR002BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 12.500f && mCurrent <= 18.750f) {
			// //brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR003BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 18.750 && mCurrent < 25.000f) {

			// //brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR004BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 25.000f && mCurrent <= 31.250f) {

			// //brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR005BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 31.250f && mCurrent <= 37.500f) {

			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR006BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 37.500f && mCurrent <= 43.750f) {

			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR007BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 43.750f && mCurrent <= 50.000f) {

			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR008BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 50.000f && mCurrent <= 56.250f) {

			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR009BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 56.250f && mCurrent <= 62.500f) {

			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR010BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 62.500f && mCurrent <= 68.750f) {

			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR011BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 75.000f && mCurrent <= 81.250f) {

			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR012BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 81.250f && mCurrent <= 87.500f) {

			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR013BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 87.500f && mCurrent <= 93.750f) {

			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR014BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 93.750f && mCurrent < 97.000f) {

			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR015BW", MainActivity.this);
			Log.d("progress", "progress=" + mCurrent);
		} else if (mCurrent > 97.000f && mCurrent < 100.000f) {
			// brightnesSeekBar.setProgress((int) mCurrent);
			Bluetooth.sendMessage("AT#IR016BW", MainActivity.this);
			Log.d("progress", "100");
			if (mCurrent > 100.000f) {
				mCurrent = 100.000f;
				Bluetooth.sendMessage("AT#IR016BW", MainActivity.this);
			}

		}

	}

	// 退出程序时存储当前的播放状态
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// 获取播放状态

		brightnesSeekBar.setProgress((int) mCurrent);

		super.onBackPressed();
	}

	public String numAdjust(int Number) {
		String result;
		result = Integer.toString(Number);
		if (result.length() < 2) {
			return ("0" + result);
		} else
			return result;
	}

	@Override
	public synchronized void onPause() {
		super.onPause();
		if (Bluetooth.deBug())
			Log.e(Bluetooth.DEVICE_NAME, "- ON PAUSE -");
	}

	@Override
	public void onStop() {
		super.onStop();
		if (Bluetooth.deBug())
			Log.e(Bluetooth.DEVICE_NAME, "-- ON STOP --");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Stop the Bluetooth chat services
		if (Bluetooth.mChatService != null)
			Bluetooth.mChatService.stop();
		if (Bluetooth.deBug())
			Log.e(Bluetooth.DEVICE_NAME, "--- ON DESTROY ---");
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	/**
	 * 设置蓝牙可被发现300
	 */
	private void ensureDiscoverable() {
		if (Bluetooth.deBug())
			Log.d(Bluetooth.DEVICE_NAME, "ensure discoverable");
		if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
			Intent discoverableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(
					BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			startActivity(discoverableIntent);
		}
	}

	public void setupChat() {
		Log.d(Bluetooth.DEVICE_NAME, "setupChat()");
		SendHandler mHandler = new SendHandler(MainActivity.this);
		// Initialize the BluetoothChatService to perform bluetooth connections
		Bluetooth.mChatService = new BluetoothChatService(MainActivity.this,
				mHandler);

		// Initialize the buffer for outgoing messages
		mOutStringBuffer = new StringBuffer("");
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (Bluetooth.deBug())
			Log.d(Bluetooth.DEVICE_NAME, "onActivityResult " + resultCode);
		switch (requestCode) {
		case Bluetooth.REQUEST_CONNECT_DEVICE:
			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "read_message1");
			// When DeviceListActivity returns with a device to connect
			if (resultCode == Activity.RESULT_OK) {
				// Get the device MAC address

				String address = data.getExtras().getString(
						DeviceListActivity.EXTRA_DEVICE_ADDRESS);
				// Get the BLuetoothDevice object
				BluetoothDevice device = mBluetoothAdapter
						.getRemoteDevice(address);
				// Attempt to connect to the device
				Bluetooth.mChatService.connect(device);

			}
			break;
		case Bluetooth.REQUEST_ENABLE_BT:
			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "read_message2");
			// When the request to enable Bluetooth returns
			if (resultCode == Activity.RESULT_OK) {
				// Bluetooth is now enabled, so set up a chat session
				setupChat();
			} else {
				// User did not enable Bluetooth or an error occured
				Log.d(Bluetooth.DEVICE_NAME, "BT not enabled");
				Toast.makeText(this, R.string.bt_not_enabled_leaving,
						Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (Bluetooth.deBug())
			Log.e(Bluetooth.DEVICE_NAME, "read_message3");
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.scan:
			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "read_message4");
			// Launch the DeviceListActivity to see devices and do scan
			Intent serverIntent = new Intent(this, DeviceListActivity.class);
			startActivityForResult(serverIntent,
					Bluetooth.REQUEST_CONNECT_DEVICE);
			return true;
		case R.id.discoverable:
			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "read_message5"); // Ensure this
																// device is
			// discoverable by others
			ensureDiscoverable();
			return true;
		}

		return false;
	}

	/*
	 * home to master Act
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			confirm();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/*
	 * close the act
	 */
	public void confirm() {
		// TODO Auto-generated method stub
		AlertDialog.Builder exitSure = new AlertDialog.Builder(
				MainActivity.this);
		// exitSure.setTitle("Cancle");
		exitSure.setMessage(R.string.close_spp);
		exitSure.setPositiveButton(R.string.yes,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						MainActivity.this.finish();
						// close Activity
					}
				});
		exitSure.setNegativeButton(R.string.no,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
		exitSure.show();
	}

	

	private void initActionBar() {

		backaActionBar = getActionBar();
		if (backaActionBar != null) {
			
			Resources res = getResources();
			Drawable myDrawable = res.getDrawable(R.drawable.theme_bg);
			backaActionBar.setBackgroundDrawable(myDrawable);
			backaActionBar.setTitle("                    SAYAO LIGHTING");
			backaActionBar.setLogo(myDrawable);
			backaActionBar.setDisplayHomeAsUpEnabled(false);
			backaActionBar.setDisplayShowHomeEnabled(false);
			
		}

	}
}
