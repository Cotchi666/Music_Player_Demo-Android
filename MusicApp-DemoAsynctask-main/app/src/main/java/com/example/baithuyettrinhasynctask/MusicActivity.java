package com.example.baithuyettrinhasynctask;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class MusicActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView imageView_pause;
    TextView textView_trai, textView_phai;
    SeekBar seekBar_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        anhxa();
        mediaPlayer = MediaPlayer.create(this, R.raw.tron_tim);
        mediaPlayer.start();
        seekBarMusic();
    }

    private void anhxa() {
        imageView_pause = findViewById(R.id.imageView_pause);
        seekBar_music = findViewById(R.id.seekBar_music);
        textView_phai = findViewById(R.id.textView_phai);
        textView_trai = findViewById(R.id.textView_trai);
    }

    private void seekBarMusic() {
        int tgBaiHat = mediaPlayer.getDuration();
        textView_phai.setText(new SimpleDateFormat("mm:ss").format(tgBaiHat));
        seekBar_music.setMax(tgBaiHat);
          new AsyncTask<Void, Integer, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                int i = 0;
                while (i <= tgBaiHat) {
                    i = mediaPlayer.getCurrentPosition();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                textView_trai.setText(new SimpleDateFormat("mm:ss").format(values[0]));
                seekBar_music.setProgress(values[0] / 1000);
            }
        }.execute();
    }

    private void stop() {
        mediaPlayer.stop();
    }

    private void pause() {
        if (mediaPlayer.isPlaying()) {
            imageView_pause.setImageResource(R.drawable.ic_pause);
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
            imageView_pause.setImageResource(R.drawable.icon_play);
        }

    }

    public void click_music(View view) {
        switch (view.getId()) {
            case R.id.imageView_pause:
                pause();
                break;
            case R.id.imageView_stop:
                stop();
                break;
        }

    }
}