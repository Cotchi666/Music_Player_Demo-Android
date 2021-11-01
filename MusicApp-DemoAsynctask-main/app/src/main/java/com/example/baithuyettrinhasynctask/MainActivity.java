package com.example.baithuyettrinhasynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button_run;
    SeekBar seekBar_run;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,MusicActivity.class));
        seekBar_run = findViewById(R.id.seekBar_run);
        button_run = findViewById(R.id.button_run);
        textView = findViewById(R.id.textView);
        button_run.setOnClickListener(v -> {
            run();
        });
    }

    private void run() {
        new AsyncTask<Void, Integer, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < 101; i++) {
                    SystemClock.sleep(100);
                    publishProgress(i);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                textView.setText(values[0] + "%");
                seekBar_run.setProgress(values[0]);
            }
        }.execute();
    }
}