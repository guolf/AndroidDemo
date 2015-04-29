package cn.guolf.androiddemo.media;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

/**
 * ¼��
 * Created by glf on 2015/4/29.
 */
public class Recorder implements  IVoiceManager {
    private String path;
    private MediaRecorder mRecorder;
    private  String TAG= this.getClass().getName();

    public Recorder(String path){
        this.path= path;
        mRecorder= new MediaRecorder();
    }

    @Override
    public boolean start() {
        //������ԴΪMicphone
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //���÷�װ��ʽ
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(path);
        //���ñ����ʽ
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
        //¼��
        mRecorder.start();
        return false;
    }

    @Override
    public boolean stop() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        return false;
    }
}

