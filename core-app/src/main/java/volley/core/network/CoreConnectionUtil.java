package volley.core.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CoreConnectionUtil {

    public enum ConnectionType {WIFI, MOBILE, OTHER_TYPE, NO_CONNECTION}

    /**
     * Determines the connection type, including no connection
     *
     * @param context to get system service
     * @return Connection Type - WIFI / MOBILE / or OTHER -- NO CONNECTION
     */
    private ConnectionType checkNetworkConnection(Context context) {

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();

        if (activeInfo != null && activeInfo.isConnected()) {
            switch (activeInfo.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return ConnectionType.WIFI;

                case ConnectivityManager.TYPE_MOBILE:
                    return ConnectionType.MOBILE;

                default:
                    return ConnectionType.OTHER_TYPE;
            }

        } else {
            return ConnectionType.NO_CONNECTION;
        }
    }
}
