package com.hejunlin.liveplayback.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HM on 2017/5/4 11:33
 */

public class WifiUtils {
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public enum WifiCipherType {
        WIFICIPHER_WEP, WIFICIPHER_WPA, WIFICIPHER_NOPASS, WIFICIPHER_INVALID
    }

    public static android.net.DhcpInfo getDhcpInfo(Context mContext) {

        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        DhcpInfo di = wm.getDhcpInfo();

        return di;
    }

    public static String long2ip(long ip) {

        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf((int) (ip & 0xff)));
        sb.append('.');
        sb.append(String.valueOf((int) ((ip >> 8) & 0xff)));
        sb.append('.');
        sb.append(String.valueOf((int) ((ip >> 16) & 0xff)));
        sb.append('.');
        sb.append(String.valueOf((int) ((ip >> 24) & 0xff)));
        return sb.toString();
    }

    public static WifiCipherType getWifiCipher(String capability) {

        String cipher = getEncryptString(capability);

        if (cipher.contains("WEP")) {

            return WifiCipherType.WIFICIPHER_WEP;
        } else if (cipher.contains("WPA") || cipher.contains("WPA2") || cipher.contains("WPS")) {

            return WifiCipherType.WIFICIPHER_WPA;
        } else if (cipher.contains("unknow")) {

            return WifiCipherType.WIFICIPHER_INVALID;
        } else {
            return WifiCipherType.WIFICIPHER_NOPASS;
        }
    }

    public static String getEncryptString(String capability) {


        StringBuilder sb = new StringBuilder();

        if (TextUtils.isEmpty(capability))
            return "unknow";

        if (capability.contains("WEP")) {

            sb.append("WEP");

            return sb.toString();
        }

        if (capability.contains("WPA")) {

            sb.append("WPA");

        }
        if (capability.contains("WPA2")) {

            sb.append("/");

            sb.append("WPA2");

        }

        if (capability.contains("WPS")) {

            sb.append("/");

            sb.append("WPS");

        }

        if (TextUtils.isEmpty(sb))
            return "OPEN";

        return sb.toString();
    }

    public static List<WifiConfiguration> getConfigurations(Context mContext) {

        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        List<WifiConfiguration> mList = wm.getConfiguredNetworks();

        return mList;
    }

    public static boolean removeWifi(Context mContext, int networkId) {
        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        return wm.removeNetwork(networkId);

    }

    public static WifiConfiguration getHistoryWifiConfig(Context mContext, String ssid) {

        WifiManager mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        List<WifiConfiguration> localList = mWifiManager.getConfiguredNetworks();

        for (WifiConfiguration wc : localList) {
            if (("\"" + ssid + "\"").equals(wc.SSID)) {
                return wc;
            } else if (ssid.equals(wc.SSID)) {
                return wc;
            }
            mWifiManager.disableNetwork(wc.networkId);
        }
        return null;
    }

    private static int addNetWork(WifiConfiguration cfg, Context mContext) {

        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        if (!wm.isWifiEnabled())
            wm.setWifiEnabled(true);

        WifiInfo mInfo = wm.getConnectionInfo();

        if (mInfo != null) {

            wm.disableNetwork(mInfo.getNetworkId());
            wm.disconnect();
        }

        boolean flag = false;

        WifiConfiguration temp = getHistoryWifiConfig(mContext, cfg.SSID);
        if (temp != null) {
            wm.removeNetwork(temp.networkId);
        }

        cfg.networkId = wm.addNetwork(cfg);

        return cfg.networkId;
    }

    public static boolean editNetWork(WifiConfiguration cfg, Context mContext) {

        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        addNetWork(cfg, mContext);

        return wm.enableNetwork(cfg.networkId, true);
    }

    public static boolean editStaticWifiConfig(WifiConfiguration cfg, Context mContext, String ip, String gateway, int prefixLength, String dns) {

        boolean flag = false;

        addNetWork(cfg, mContext);

        try {
            setIpAssignment("STATIC", cfg);
            setIpAddress(InetAddress.getByName(ip), prefixLength, cfg);
            setGateway(InetAddress.getByName(gateway), cfg);
            setDNS(InetAddress.getByName(dns), cfg);


            WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

            wm.updateNetwork(cfg); //apply the setting

            flag = wm.enableNetwork(cfg.networkId, true);

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }

    public static boolean editDhcpNetWork(WifiConfiguration cfg, Context mContext) {

        boolean flag = false;

        addNetWork(cfg, mContext);

        try {
            setIpAssignment("DHCP", cfg);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //"STATIC" or "DHCP" for dynamic setting

        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        wm.updateNetwork(cfg); //apply the setting

        flag = wm.enableNetwork(cfg.networkId, true);

        return flag;
    }

    public static WifiConfiguration createWifiConfig(String SSID, String Password,

                                                     WifiCipherType Type) {

        WifiConfiguration config = new WifiConfiguration();

        config.allowedAuthAlgorithms.clear();

        config.allowedGroupCiphers.clear();

        config.allowedKeyManagement.clear();

        config.allowedPairwiseCiphers.clear();

        config.allowedProtocols.clear();

        if (!SSID.startsWith("\"")) {

            SSID = "\"" + SSID + "\"";
        }
        config.SSID = SSID;

        // 无密码

        if (Type == WifiCipherType.WIFICIPHER_NOPASS) {

            config.wepKeys[0] = "\"" + "\"";

            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);

            config.wepTxKeyIndex = 0;

        }

        // WEP加密

        if (Type == WifiCipherType.WIFICIPHER_WEP) {

            config.preSharedKey = "\"" + Password + "\"";

            config.hiddenSSID = true;

            config.allowedAuthAlgorithms

                    .set(WifiConfiguration.AuthAlgorithm.SHARED);

            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);

            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);

            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);

            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);

            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);

            config.wepTxKeyIndex = 0;

        }

        // WPA加密

        if (Type == WifiCipherType.WIFICIPHER_WPA) {

            config.preSharedKey = "\"" + Password + "\"";

            config.hiddenSSID = true;

            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            config.status = WifiConfiguration.Status.ENABLED;

        }

        return config;

    }

    public static WifiInfo getConnectedWifiInfo(Context mContext) {

        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        return wm.getConnectionInfo();

    }

    /**
     * 获取扫描结果
     *
     * @param mContext
     * @return
     */
    public static List<ScanResult> getWifiScanResult(Context mContext) {


        List<ScanResult> mResult = new ArrayList<ScanResult>();

        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        mResult = wm.getScanResults();

        if (mResult != null) {

            for (ScanResult mRs : mResult) {

                Log.d(WifiUtils.class.getSimpleName(), mRs.toString());
            }
        }

        return mResult;
    }

    /**
     * @param mContext
     * @return
     * @ToDo 有些机型在未开启wifi功能是不能够获取网卡地址和mac地址，获取到ip是0
     * @Date 2014-3-19
     */
    public static String getWlanMacFromWifi(Context mContext) {

        String wlanMac = "";

        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        WifiInfo info = (wm == null ? null : wm.getConnectionInfo());

        if (info != null) {

            wlanMac = info.getMacAddress();

        }

        if (TextUtils.isEmpty(wlanMac)) {

            boolean isOpen = false;
            //判断是否开启wifi
            if (!wm.isWifiEnabled()) {
                wm.setWifiEnabled(true);
            } else {

                isOpen = true;
            }

            WifiInfo info2 = (wm == null ? null : wm.getConnectionInfo());

            if (info2 != null) {

                wlanMac = info2.getMacAddress();

                if (!isOpen)
                    wm.setWifiEnabled(false);

            }
        }
        return wlanMac;
    }

    public static boolean isWifiOpen(Context mContext) {

        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        return wm.isWifiEnabled();

    }

    public static void openWifi(final Context mContext, final IWifiOpen mCallBack) {

        new Thread(
                new Runnable() {

                    @Override
                    public void run() {

                        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

                        wm.setWifiEnabled(true);

                        while (wm.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {

                        }

                        Log.d(WifiUtils.class.getSimpleName(), "openWifi finish... " + wm.getWifiState());

                        if (mCallBack != null) {

                            mCallBack.onWifiOpen(wm.getWifiState());
                        }
                    }


                }).start();

    }

    public interface IWifiOpen {

        public void onWifiOpen(int state);
    }

    /////---设置高级选项-----/////
    public static void setIpAssignment(String assign, WifiConfiguration wifiConf) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        setEnumField(wifiConf, assign, "ipAssignment");
    }

    public static void setIpAddress(InetAddress addr, int prefixLength, WifiConfiguration wifiConf) throws SecurityException, IllegalArgumentException,
            NoSuchFieldException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        Object linkProperties = getField(wifiConf, "linkProperties");
        if (linkProperties == null)
            return;
        Class laClass = Class.forName("android.net.LinkAddress");
        Constructor laConstructor = laClass.getConstructor(new Class[]{InetAddress.class, int.class});
        Object linkAddress = laConstructor.newInstance(addr, prefixLength);
        ArrayList mLinkAddresses = (ArrayList) getDeclaredField(linkProperties, "mLinkAddresses");
        mLinkAddresses.clear();
        mLinkAddresses.add(linkAddress);
    }

    public static void setGateway(InetAddress gateway, WifiConfiguration wifiConf) throws SecurityException, IllegalArgumentException,
            NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Object linkProperties = getField(wifiConf, "linkProperties");
        if (linkProperties == null)
            return;
        Class routeInfoClass = Class.forName("android.net.RouteInfo");
        Constructor routeInfoConstructor = routeInfoClass.getConstructor(new Class[]{InetAddress.class});
        Object routeInfo = routeInfoConstructor.newInstance(gateway);
        ArrayList mRoutes = (ArrayList) getDeclaredField(linkProperties, "mRoutes");
        mRoutes.clear();
        mRoutes.add(routeInfo);
    }

    public static void setDNS(InetAddress dns, WifiConfiguration wifiConf) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        Object linkProperties = getField(wifiConf, "linkProperties");
        if (linkProperties == null)
            return;
        ArrayList<InetAddress> mDnses = (ArrayList<InetAddress>) getDeclaredField(linkProperties, "mDnses");
        mDnses.clear(); // or add a new dns address , here I just want to replace DNS1
        mDnses.add(dns);
    }

    public static String getNetworkPrefixLength(WifiConfiguration wifiConf) {
        String address = "";
        try {
            Object linkProperties = getField(wifiConf, "linkProperties");
            if (linkProperties == null)
                return null;

            if (linkProperties != null) {
                ArrayList mLinkAddresses = (ArrayList) getDeclaredField(linkProperties, "mLinkAddresses");
                if (mLinkAddresses != null && mLinkAddresses.size() > 0) {
                    Object linkAddressObj = mLinkAddresses.get(0);
                    address = linkAddressObj.getClass().getMethod("getNetworkPrefixLength", new Class[]{}).invoke(linkAddressObj, (Object) null) + "";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    public static InetAddress getIpAddress(WifiConfiguration wifiConf) {
        InetAddress address = null;
        try {
            Object linkProperties = getField(wifiConf, "linkProperties");
            if (linkProperties == null)
                return null;

            if (linkProperties != null) {
                ArrayList mLinkAddresses = (ArrayList) getDeclaredField(linkProperties, "mLinkAddresses");
                if (mLinkAddresses != null && mLinkAddresses.size() > 0) {
                    Object linkAddressObj = mLinkAddresses.get(0);
                    address = (InetAddress) linkAddressObj.getClass().getMethod("getAddress", new Class[]{}).invoke(linkAddressObj, (Object) null);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    public static InetAddress getGateway(WifiConfiguration wifiConf) {
        InetAddress address = null;
        try {
            Object linkProperties = getField(wifiConf, "linkProperties");

            if (linkProperties != null) {
                ArrayList mRoutes = (ArrayList) getDeclaredField(linkProperties, "mRoutes");
                if (mRoutes != null && mRoutes.size() > 0) {
                    Object linkAddressObj = mRoutes.get(0);
                    address = (InetAddress) linkAddressObj.getClass().getMethod("getGateway", new Class[]{}).invoke(linkAddressObj, (Object) null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    public static InetAddress getDNS(WifiConfiguration wifiConf) {
        InetAddress address = null;
        try {
            Object linkProperties = getField(wifiConf, "linkProperties");

            if (linkProperties != null) {
                ArrayList<InetAddress> mDnses = (ArrayList<InetAddress>) getDeclaredField(linkProperties, "mDnses");
                if (mDnses != null && mDnses.size() > 0) {
                    address = (InetAddress) mDnses.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return address;
    }

    public static Object getField(Object obj, String name) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field f = obj.getClass().getField(name);
        Object out = f.get(obj);
        return out;
    }

    public static Object getDeclaredField(Object obj, String name) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field f = obj.getClass().getDeclaredField(name);
        f.setAccessible(true);
        Object out = f.get(obj);
        return out;
    }

    public static void setEnumField(Object obj, String value, String name) throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {
        Field f = obj.getClass().getField(name);
        f.set(obj, Enum.valueOf((Class<Enum>) f.getType(), value));
    }
}
