package com.android.discovery.broadcastvoiceservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private String path = "http://vcast-resource.cdn.bcebos.com/vcast-resource/ffeeefa9-e5d4-4413-ba25-def479d5b8b0.mp3";
    private Player mPlayer;
    private EditText mEditText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.editText);

        mEditText.setText(path);
        button = findViewById(R.id.button_start);
        button.setOnClickListener(this);
        mPlayer = new Player();

        service();
    }

    public void sendBroadCast() {
        Intent intent = new Intent("com.android.discovery.action.VoiceBroadcast");
        //发送显示广播，设置广播接收者的路径:第一个参数是包名路径；第二个参数是类名路径
       /* intent.setComponent(new ComponentName("com.example.broadcaststudy",
                "com.example.broadcaststudy.MyBroadcastReceiver"));*/
        //创建Bundle
        Bundle bundle = new Bundle();
        String content  = mEditText.getText().toString();
        //储存要发送的广播消息内容
        //  bundle.putString("message",content);
        intent.putExtra("message",content);
        //发送广播
        sendBroadcast(intent);
    }
    public void service(){
        Intent intent = new Intent(this, BroadCastVoiceService.class);
        Bundle bundle = new Bundle();
        // bundle.putSerializable("Key", VoiceBroadcastService.Control.PLAY);
        intent.putExtras(bundle);
        startService(intent);
    }

    /**
     * 播放
     *
     * @param
     */
    public void play() {
        new Thread(new Runnable() {

            @Override
            public void run() {

                mPlayer.playUrl(mEditText.getText().toString().trim());
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_start:
                play();
                sendBroadCast();



        }
    }
}
