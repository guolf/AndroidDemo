package cn.guolf.androiddemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;

import cn.guolf.androiddemo.R;
import cn.guolf.androiddemo.camera.CallbackContext;
import cn.guolf.androiddemo.camera.CameraConfig;
import cn.guolf.androiddemo.camera.CameraLauncher;
import cn.guolf.androiddemo.util.LogUtil;

/**
 * WebViewÅÄÕÕ¡¢Ñ¡ÔñÎÄ¼þ
 */
public class CameraActivity extends ActionBarActivity {

    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        webview = (WebView)findViewById(R.id.webview);
        initWebViewSettings();
    }

    private void initWebViewSettings(){
        WebSettings settings =  webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new MyWebViewClient());
        webview.setWebChromeClient(new CustomWebChromeClient());
        webview.loadUrl("file:///android_asset/camera.html");
    }

    private ValueCallback<Uri> mUploadMessage;
    private final static int FILECHOOSER_RESULTCODE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        if (requestCode == FILECHOOSER_RESULTCODE) {
//            if (null == mUploadMessage)
//                return;
//            Uri result = intent == null || resultCode != RESULT_OK ? null
//                    : intent.getData();
//            mUploadMessage.onReceiveValue(result);
//            mUploadMessage = null;
//        }
        camera.onActivityResult(requestCode,resultCode,intent);
    }

    CameraLauncher camera = new CameraLauncher(this);
    CameraConfig config = new CameraConfig();

    protected class CustomWebChromeClient extends WebChromeClient {
        // For Android > 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileChooser(uploadMsg);
        }

        // Andorid 3.0 +
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            openFileChooser(uploadMsg);
        }

        //Android 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mUploadMessage = uploadMsg;
//            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//            i.addCategory(Intent.CATEGORY_OPENABLE);
//            i.setType("*/*");
//            startActivityForResult(Intent.createChooser(i, "Image Browser"),FILECHOOSER_RESULTCODE);
            try {
                camera.execute(config.getConfig(), new CallbackContext() {

                    @Override
                    public void success(String message) {
                        LogUtil.i(message);
                    }

                    @Override
                    public void error(String message) {
                        LogUtil.i(message);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
