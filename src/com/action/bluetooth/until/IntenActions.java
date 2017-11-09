package com.action.bluetooth.until;

import com.action.bluetooth.ColorSettings;
import com.action.bluetooth.DelayTimeSettings;
import com.action.bluetooth.LightSettings;
import com.action.bluetooth.LightSettings;
import com.action.bluetooth.TimeSettings;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class IntenActions {

	private static Intent intent;
	public static String ACTIONS_COLOR = "color";
	public static String ACTIONS_LIGHT = "light";
	public static String ACTIONS_TIME = "time";
	public static String ACTIONS_DELAY = "delay";
	

	public static Intent getItemIntent(Context mContext, String actionName) {
		intent = null;

		if (actionName.equals("color")) {

			if (intent == null) {
				intent = new Intent(mContext.getApplicationContext(),
						ColorSettings.class);
				mContext.startActivity(intent);
			} else {
				//Toast.makeText(mContext, "device null", 200).show();
			}

		} else if (actionName.equals("light")) {

			if (intent == null) {
				intent = new Intent(mContext.getApplicationContext(),
						LightSettings.class);
				mContext.startActivity(intent);
			} else {
				//Toast.makeText(mContext, "device null", 200).show();
			}

		} else if (actionName.equals("time")) {
			if (intent == null) {
				intent = new Intent(mContext.getApplicationContext(),
						TimeSettings.class);
				mContext.startActivity(intent);
			} else {
				//Toast.makeText(mContext, "device null", 200).show();
			}

		} else if (actionName.equals("delay")) {
			if (intent == null) {
				intent = new Intent(mContext.getApplicationContext(),
						DelayTimeSettings.class);
				mContext.startActivity(intent);
			} else {
				//Toast.makeText(mContext, "device null", 200).show();
			}

		} else if (actionName.equals("")) {
			// Log.DEBUG();
		}
		return intent;
	}

}
