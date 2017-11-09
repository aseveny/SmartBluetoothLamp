package com.action.bluetooth.chat;

import android.bluetooth.BluetoothDevice;

import java.lang.reflect.Method;

/**
 * Created by nianyuguai on 13-9-28.
 */
public class BluetoothPaired {
    static public boolean createBond(Class btClass,BluetoothDevice btDevice)throws Exception{
        Method createBondMethod = btClass.getMethod("createBond");
        Boolean returnValue = (Boolean)createBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }

    static public boolean removeBond(Class btClass,BluetoothDevice btDevice)throws Exception{
        Method removeBondMethod = btClass.getMethod("removeBond");
        Boolean  returnValue = (Boolean)removeBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }
}


