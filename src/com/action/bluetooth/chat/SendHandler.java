package com.action.bluetooth.chat;

import com.action.bluetooth.until.Bluetooth;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class SendHandler extends Handler {

	public Context mContext;
	public static ArrayAdapter<String> mConversationArrayAdapter;
	public static String mConnectedDeviceName = null;

	public SendHandler(Context context) {
		super();
		this.mContext = context;
	}

	public SendHandler(Looper looper) {
		super(looper);
	}

	@Override
	public void handleMessage(Message msg) {
		// setupChat();
		switch (msg.what) {
		case Bluetooth.MESSAGE_STATE_CHANGE:
			if (Bluetooth.deBug())
				Log.i(Bluetooth.DEVICE_NAME, "MESSAGE_STATE_CHANGE: "
						+ msg.arg1);
			switch (msg.arg1) {
			case BluetoothChatService.STATE_CONNECTED:

				mConversationArrayAdapter.clear();
				break;
			case BluetoothChatService.STATE_CONNECTING:

				Log.d("Bluetooth", "display is connected");
				break;
			case BluetoothChatService.STATE_LISTEN:
			case BluetoothChatService.STATE_NONE:

				Log.d("Bluetooth", "display not_connected");
				break;
			}
			break;
		case Bluetooth.MESSAGE_WRITE:
			byte[] writeBuf = (byte[]) msg.obj;
			// construct a string from the buffer
			String writeMessage = new String(writeBuf);

			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "listview");
			mConversationArrayAdapter.add("Me:  " + writeMessage);
			break;
		case Bluetooth.MESSAGE_READ:
			byte[] readBuf = (byte[]) msg.obj;
			// construct a string from the valid bytes in the buffer
			String readMessage = new String(readBuf, 0, msg.arg1);
			if (Bluetooth.deBug())
				Log.e(Bluetooth.DEVICE_NAME, "read_message");
			mConversationArrayAdapter.add(mConnectedDeviceName + ":  "
					+ readMessage);
			break;
		case Bluetooth.MESSAGE_DEVICE_NAME:
			// save the connected device's name
			mConnectedDeviceName = msg.getData().getString(
					Bluetooth.DEVICE_NAME);
			Toast.makeText(mContext.getApplicationContext(),
					"Connected to " + mConnectedDeviceName, Toast.LENGTH_SHORT)
					.show();
			break;
		case Bluetooth.MESSAGE_TOAST:
			Toast.makeText(mContext.getApplicationContext(),
					msg.getData().getString(Bluetooth.TOAST),
					Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
