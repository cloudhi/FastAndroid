package com.github.yoojia.fast;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * 获取设备的标识字符串
 *
 * @author  yoojia.chen@gmail.com
 * @version 2015-04-09
 * @since   1.0
 */
public class MobileID {

    /**
     * 返回一个设备标识字符串
     * @param context Context
     * @return 设备标识字符串
     */
    public static String getMId(Context context){
        StringBuilder buf = new StringBuilder(buildId());
        buf.append(getIMEI(context));
        buf.append(getWIFIMAC(context));
        buf.append(getBluetoothMAC());
        return HashEncryption.encrypt(buf.toString(), "MD5");
    }

    /**
     * 获取IMEI码
     * @param context Context
     * @return IMEI码，如果没有读取设备权限，返回空字符串。
     */
    public static String getIMEI(Context context){
        TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            String imei = TelephonyMgr.getDeviceId();
            return null == imei ? "" : imei;
        }catch (Exception e){
            return "";
        }
    }

    public static String buildId(){
        return "35" +
                Build.BOARD.length()%10
                + Build.BRAND.length()%10
                + Build.CPU_ABI.length()%10
                + Build.DEVICE.length()%10
                + Build.DISPLAY.length()%10
                + Build.HOST.length()%10
                + Build.ID.length()%10
                + Build.MANUFACTURER.length()%10
                + Build.MODEL.length()%10
                + Build.PRODUCT.length()%10
                + Build.TAGS.length()%10
                + Build.TYPE.length()%10
                + Build.USER.length()%10 ;
    }

    /**
     * 获取Wifi的MAC地址
     * @param context Context
     * @return MAC地址，如果没有Wifi设备或者没有读取设备权限，返回空字符串。
     */
    public static String getWIFIMAC(Context context){
        WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        try {
            String mac = wm.getConnectionInfo().getMacAddress();
            return null == mac ? "" : mac;
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 获取蓝牙的Mac地址
     * @return 蓝牙的MAC地址，如果没有蓝牙设备或者没有读取设备权限，返回空字符串。
     */
    public static String getBluetoothMAC(){
        try {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            String BT_MAC = adapter.getAddress();
            return null == BT_MAC ? "" : BT_MAC;
        }catch (Exception e){
            return "";
        }
    }

}
