package com.example.baithuyettrinhasynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Asynctask extends AppCompatActivity {
    private Button btn;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask);
        btn = (Button) findViewById(R.id.buttonAs);
        txt = (TextView) findViewById(R.id.textviewAs);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Myasynctask().execute();
            }
        });

    }

    private class Myasynctask extends AsyncTask<Void, String, String> {

        private int a;
        private String str;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txt.setText("Begin" +"\n");
        }

            @Override
            protected String doInBackground(Void... voids) {
            for(int i = 1 ;i <= 5; i++ ){
                publishProgress("Task" + i + "\n");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return "done";

        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txt.append(s);
        }
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
//            txt.setText(values[0]);
            txt.append(values[0]);

        }
    }

//    private class Myasynctask extends AsyncTask<Void, Integer, String> {
//
//        private int count;
//        private String str;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            txt.setText("Begin" +"\n");
//        }
//
//                @Override
//        protected String doInBackground(Void... voids) {
//            while (count < 10){
//                count++;
//                publishProgress(count);
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            return "Done!";
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            txt.append(s);
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            txt.setText(String.valueOf(values[0]));
//
//        }
//
//    }
}


