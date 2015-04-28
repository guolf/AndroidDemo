package cn.guolf.androiddemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import java.util.ArrayList;

public class NetBroadcastReceiver extends BroadcastReceiver {
    public static ArrayList<netEventHandler> mListeners = new ArrayList<netEventHandler>();
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    public NetBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(NET_CHANGE_ACTION)) {
            if (intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)) {
                for (netEventHandler handler : mListeners) {
                    handler.onNetChangeNo();
                }
            } else {
                for (netEventHandler handler : mListeners) {
                    handler.onNetChangeYes();
                }
            }
        }

    }

    public interface netEventHandler {

          void onNetChangeYes();

          void onNetChangeNo();
    }
}
