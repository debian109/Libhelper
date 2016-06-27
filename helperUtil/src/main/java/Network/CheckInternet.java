package Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by Nam Tran on 09-Dec-15.
 * Describes : Check the current network connection
 */
public class CheckInternet {

    /**
     * ConnectivityManager is class that answers queries about the state of network connectivity. It also
     * notifies applications when networkconnectivity changes. Get an instance of this class by
     * calling Context.getSystemService(Context.CONNECTIVITY_SERVICE).
     * The primary responsibilities of this class are to:
        - Monitor network connections (Wi-Fi, GPRS, UMTS, etc.)
        - Send broadcast intents when network connectivity changes
        - Attempt to "fail over" to another network when connectivity to a network is lost
        - Provide an API that allows applications to query the coarse-grained or fine-grained state of the available networks
        - Provide an API that allows applications to request and select networks for their data traffic
     */
    public static final int SIM_OK = 0;
    public static final int SIM_NO = -1;
    public static final int SIM_UNKNOW = -2;

    public static final String CONN_TYPE_WIFI = "wifi";
    public static final String CONN_TYPE_GPRS = "gprs";
    public static final String CONN_TYPE_NONE = "none";

    /**
     * Determine the network connection is active
     *
     * @return Determine the network connection is active
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = manager.getAllNetworkInfo();
        for (NetworkInfo info : infos) {
            if (info.isConnected()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether the current network is wifi network
     *
     * @param context
     * @return boolean Is wifi network
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * Determine whether the current network is 3g network
     *
     * @param context
     * @return Is 3g network
     */
    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * Obtain network type，wifi 2G 3G
     *
     * @param context
     * @return WF 2G 3G 4G，Or null if no network
     */
    public static String getWifiOr2gOr3G(Context context) {
        String networkType = "";
        if (context != null) {
            try {
                ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetInfo = cm.getActiveNetworkInfo();
                if (activeNetInfo != null && activeNetInfo.isConnectedOrConnecting()) { // With a network
                    networkType = activeNetInfo.getTypeName().toLowerCase();
                    if (networkType.equals("wifi")) {
                        networkType = "WF";
                    } else { // mobile network
                        // //If you use a mobile network, you get apn
                        // apn = activeNetInfo.getExtraInfo();
                        // The specific type of mobile network normalized to 2G 3G 4G
                        networkType = "2G"; // The default is 2G
                        int subType = activeNetInfo.getSubtype();
                        switch (subType) {
                            case TelephonyManager.NETWORK_TYPE_1xRTT:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_CDMA: // IS95
                                break;
                            case TelephonyManager.NETWORK_TYPE_EDGE: // 2.75
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_GPRS: // 2.5
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSDPA: // 3.5
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSPA: // 3.5
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSUPA:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_UMTS:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_EHRPD:
                                networkType = "3G";
                                break; // ~ 1-2 Mbps
                            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                                networkType = "3G";
                                break; // ~ 5 Mbps
                            case TelephonyManager.NETWORK_TYPE_HSPAP:
                                networkType = "3G";
                                break; // ~ 10-20 Mbps
                            case TelephonyManager.NETWORK_TYPE_IDEN:
                                break; // ~25 kbps
                            case TelephonyManager.NETWORK_TYPE_LTE:
                                networkType = "4G";
                                break; // ~ 10+ Mbps
                            default:
                                break;
                        }
                    } // end Mobile network ip
                } // end If there are network
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            }
        }
        return networkType;
    }

    /**
     * Analyzing the program starts active network type
     *
     * @param context
     * @return Network Type
     */
    public static String getNetworkType(Context context) {
        String result = null;


        ConnectivityManager connectivity = (ConnectivityManager) (context.getSystemService(Context.CONNECTIVITY_SERVICE));

        if (connectivity == null) {
            result = null;
        } else {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i] != null) {
                        NetworkInfo.State tem = info[i].getState();
                        if ((tem == NetworkInfo.State.CONNECTED || tem == NetworkInfo.State.CONNECTING)) {
                            String temp = info[i].getExtraInfo();
                            result = info[i].getTypeName() + " "
                                    + info[i].getSubtypeName() + temp;
                            break;
                        }
                    }
                }
            }

        }

        return result;
    }

    /**
     * Obtain network connection type
     *
     * @param context
     * @return Network connection type
     */
    public static String getNetConnectType(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == connManager) {
            return CONN_TYPE_NONE;
        }

        NetworkInfo info = null;
        info = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != info) {
            NetworkInfo.State wifiState = info.getState();
            if (NetworkInfo.State.CONNECTED == wifiState) {
                return CONN_TYPE_WIFI;
            }
        } else {
        }

        info = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (null != info) {
            NetworkInfo.State mobileState = info.getState();
            if (NetworkInfo.State.CONNECTED == mobileState) {
                return CONN_TYPE_GPRS;
            }
        } else {
        }
        return CONN_TYPE_NONE;
    }

    /**
     * Get Proxy address
     *
     * @param context
     * @return Proxy address
     */
    public static String getProxy(Context context) {
        String proxy = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
            if (networkinfo != null && networkinfo.isAvailable()) {
                String stringExtraInfo = networkinfo.getExtraInfo();
                if (stringExtraInfo != null && ("cmwap".equals(stringExtraInfo) || "uniwap".equals(stringExtraInfo))) {
                    proxy = "10.0.0.172:80";
                } else if (stringExtraInfo != null && "ctwap".equals(stringExtraInfo)) {
                    proxy = "10.0.0.200:80";
                }
            }
        }

        return proxy;
    }

    /**
     * Get SIM card status
     *
     * @param context
     * @return SIM card status
     */
    public static int getSimState(Context context) {
        TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telMgr.getSimState();
        if (simState == TelephonyManager.SIM_STATE_READY) {
            return SIM_OK;
        } else if (simState == TelephonyManager.SIM_STATE_ABSENT) {
            return SIM_NO;
        } else {
            return SIM_UNKNOW;
        }
    }

}
