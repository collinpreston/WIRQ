package org.wirq.collinhpreston.wirq;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.Nullable;

import java.io.IOException;

public class RadioClient extends IntentService {

    public static final MediaPlayer mediaPlayer = new MediaPlayer();


    public RadioClient() {
        super("RadioClient-Started");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build());
        }
        else {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
        try {
            String STATION_URL = "http://wi-icecast.monroe.edu/WIRQ-low.mp3";
            mediaPlayer.setDataSource(STATION_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
    }

}
