/**
 * @class ActivityTools.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * put here some methods which can be used in some activities
 */
public class ActivityTools {

    /**
     * @return true if the network is available and false if is not
     */
    static public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
