package cn.guolf.androiddemo.ui;

import android.app.Activity;
import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebStorage.QuotaUpdater;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.io.File;

import cn.guolf.androiddemo.R;
import cn.guolf.androiddemo.receiver.NetBroadcastReceiver;

/**
 * 实现网络状态监听、sqlite操作、页面缓存
 * Created by glf on 2015/4/28.
 */
public class WebAppActivity extends Activity implements NetBroadcastReceiver.netEventHandler {

    private WebView webview;
    private EditText etUrl;

    @SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);
        NetBroadcastReceiver.mListeners.add(this);
        webview = (WebView) findViewById(R.id.webview);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDatabaseEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);

        // 4.4及以上不需要设置
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            webview.getSettings().setDatabasePath(this.getDatabasePath("test").getPath());
        }

        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setAppCachePath(getCacheDir().getPath().concat(File.separator).concat("webviewcache"));
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.getSettings().setAllowFileAccess(true);
        webview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota,
                                                QuotaUpdater quotaUpdater) {
                quotaUpdater.updateQuota(estimatedDatabaseSize * 2);
            }
        });
        webview.loadUrl("file:///android_asset/test.html");
        etUrl = (EditText) findViewById(R.id.etUrl);
        etUrl.setOnEditorActionListener(new OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    webview.loadUrl(v.getText().toString());
                }
                return true;
            }
        });

    }

    @Override
    public void onNetChangeYes() {
        webview.setNetworkAvailable(true);
    }

    @Override
    public void onNetChangeNo() {
        webview.setNetworkAvailable(false);
    }

}
