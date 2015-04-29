package cn.guolf.androiddemo.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import cn.guolf.androiddemo.R;
import cn.guolf.androiddemo.media.Player;
import cn.guolf.androiddemo.media.Recorder;

/**
 * jsµ÷ÓÃJavaÂ¼Òô¡¢²¥·Å
 */
public class AudioRecordActivity extends ActionBarActivity {

    private Button btnStart,btnStop,btnPlayer;
    private Recorder recorder;
    private Player player;
    private String path = null;
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        path = getCacheDir().getPath() +"/1.wav";
        recorder = new Recorder(path);
        player= new Player(path);
        webview = (WebView)findViewById(R.id.webview);

        initWebViewSettings();

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnPlayer= (Button)findViewById(R.id.btnPlayer);
        btnPlayer.setOnClickListener(new AudioListerner());
        btnStart.setOnClickListener(new AudioListerner());
        btnStop.setOnClickListener(new AudioListerner());

    }

    private void initWebViewSettings(){
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new JsInteration(), "control");
        webview.setWebChromeClient(new WebChromeClient() {

        });
        webview.loadUrl("file:///android_asset/recorder.html");
    }

    public class JsInteration {

        @JavascriptInterface
        public void start(){
            Toast.makeText(AudioRecordActivity.this,"start",Toast.LENGTH_SHORT).show();
            recorder.start();
        }

        @JavascriptInterface
        public void stop(){
            Toast.makeText(AudioRecordActivity.this,"stop",Toast.LENGTH_SHORT).show();
            recorder.stop();
        }

        @JavascriptInterface
        public void player(){
            Toast.makeText(AudioRecordActivity.this,"player",Toast.LENGTH_SHORT).show();
            player.start();
        }

        @JavascriptInterface
        public  String test(){
            Toast.makeText(AudioRecordActivity.this,"test",Toast.LENGTH_SHORT).show();
            return "this is a test";
        }
    }
    class AudioListerner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.btnStart) {
                recorder.start();
            }
            if (v.getId() == R.id.btnStop) {
                recorder.stop();
            }
            if(v.getId() == R.id.btnPlayer){
                player.start();
            }
        }
    }

}
