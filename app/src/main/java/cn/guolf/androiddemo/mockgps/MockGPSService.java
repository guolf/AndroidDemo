package cn.guolf.androiddemo.mockgps;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;

import cn.guolf.androiddemo.util.LogUtil;

/**
 * –Èƒ‚Œª÷√£¨not work
 */
public class MockGPSService extends Service {

    String TAG ="MockGPSService";
    public MockGPSService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Mythread().start();
        return START_STICKY;
    }

    private class Mythread extends Thread{
        @Override
        public void run() {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.addTestProvider(LocationManager.NETWORK_PROVIDER, false, false, false, false, false, true, true, 1, 1);
            locationManager.setTestProviderEnabled(LocationManager.NETWORK_PROVIDER, true);
            while(true){
                Location loc = new Location(LocationManager.NETWORK_PROVIDER);
                loc.setTime(System.currentTimeMillis());
                loc.setLatitude(39.929986);
                loc.setLongitude(116.395645);
                locationManager.setTestProviderLocation(LocationManager.NETWORK_PROVIDER, loc);
                LogUtil.i("39.929986,116.395645");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        }
    }
}
