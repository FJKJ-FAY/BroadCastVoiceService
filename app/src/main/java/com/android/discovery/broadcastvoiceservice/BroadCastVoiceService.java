package com.android.discovery.broadcastvoiceservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class BroadCastVoiceService extends Service {
    private String STRING_BYE = "http://vcast-resource.cdn.bcebos.com/vcast-resource/ffeeefa9-e5d4-4413-ba25-def479d5b8b0.mp3";
    private String STRING_HELLO ="http://bos.nj.bpc.baidu.com/v1/developer/eb6e53a6-e7c7-4713-b017-7a35237a25bc.mp3";
    private MyReceiver myReceiver;
    private final String TAG = "BroadCastVoiceService";
    private IntentFilter intentFilter;
    private int startId;


    public BroadCastVoiceService() {
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();

        intentFilter = new IntentFilter();
        //设置监听广播的类型
        intentFilter.addAction("com.android.discovery.action.VoiceBroadcast");
        myReceiver = new MyReceiver();
        //注册广播
        registerReceiver(myReceiver,intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        Log.e(TAG, "onStartCommand---startId: " + startId);
        Bundle bundle = intent.getExtras();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        //取消注册广播
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }
    //自定义广播接受者
    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String msg=intent.getStringExtra("message");
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }

}