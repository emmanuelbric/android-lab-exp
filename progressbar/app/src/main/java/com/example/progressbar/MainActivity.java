package com.example.progressbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView tvProgress;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tv_progress);
        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(v -> new ProgressTask().execute());
    }

    private class ProgressTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress(0);
            tvProgress.setText("Progress: 0%");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(50); // Simulate some work by sleeping
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            tvProgress.setText("Progress: " + values[0] + "%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvProgress.setText("Task Completed!");
        }
    }
}
