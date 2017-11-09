package com.action.bluetooth.until;

import android.content.Context;
import android.content.SharedPreferences;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.action.bluetooth.chat.BluetoothChatService;

public class Bluetooth {

	private static final String TAG = "BluetoothChat";
	private static final boolean result = true;

	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;

	// Key names received from the BluetoothChatService Handler
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	// Intent request codes
	public static final int REQUEST_CONNECT_DEVICE = 1;
	public static final int REQUEST_ENABLE_BT = 2;

	public static StringBuffer mOutStringBuffer;
	public static BluetoothChatService mChatService;
	// private static EditText mOutEditText;

	public static ArrayAdapter<String> mConversationArrayAdapter;
	public static String mConnectedDeviceName = null;
	//private static SharedPreferences sp;
	//private static ListView mConversationView;

	public  static Context mContext;

	public Bluetooth(Context mContext) {
		super();
		Bluetooth.mContext = mContext;
	}

	public Bluetooth() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static boolean deBug() {

		return result;

	}

	
  // static SendHandler mHandler=new SendHandler(mContext);
	public static void sendMessage(String message, Context context) {
		Log.d("AT:",message);
		// Initialize the BluetoothChatService to perform bluetooth connections
		//mChatService = new BluetoothChatService(context, mHandler);

		// Initialize the buffer for outgoing messages
		mOutStringBuffer = new StringBuffer("");

		// Check that we're actually connected before trying anything
		if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
			//Toast.makeText(mContext, "You are not connected to a device", Toast.LENGTH_SHORT)
			//		.show();
			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "not connected");
			return;
		}

		// Check that there's actually something to send
		if (message.length() > 0) {
			// Get the message bytes and tell the BluetoothChatService to write
			byte[] send = message.getBytes();
			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "send1");
			mChatService.write(send);
			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "send2");

			// Reset out string buffer to zero and clear the edit text field
			mOutStringBuffer.setLength(0);
			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "send3");
			// mOutEditText.setText(mOutStringBuffer);
			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "send4");
		}
	}

}
