package com.example.progressbar2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText time;
    private TextView finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.in_time);
        button = findViewById(R.id.btn_run);
        finalResult = findViewById(R.id.tv_result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = time.getText().toString();
                runner.execute(sleepTime);
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        private AlertDialog progressDialog;

        @Override
        protected void onPreExecute() {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(false);
            builder.setView(R.layout.progress);
            progressDialog = builder.create();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0]) * 1000;
                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            // Execution of result of Long time consuming operation
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            finalResult.setText(result);
        }
    }
}
