package cn.guolf.androiddemo.media;

import android.media.MediaPlayer;
import android.util.Log;

/**
 * 播放
 * Created by glf on 2015/4/29.
 */
public class Player implements  IVoiceManager {
    private String path;
    private MediaPlayer mPlayer;
    private  String TAG= this.getClass().getName();

    public Player(String path){
        this.path=path;
        mPlayer= new MediaPlayer();
    }

    @Override
    public boolean start() {
        try {
            //设置要播放的文件
            mPlayer.setDataSource(path);
            mPlayer.prepare();
            //播放
            mPlayer.start();
        }catch(Exception e){
            Log.e(TAG, "prepare() failed");
        }
        return false;
    }

    @Override
    public boolean stop() {
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
        return false;
    }
}
