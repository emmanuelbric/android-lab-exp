package com.example.httpurll;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText urlEditText;
    private TextView dataTextView;
    private Button downloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = findViewById(R.id.urlEditText);
        dataTextView = findViewById(R.id.dataTextView);
        downloadButton = findViewById(R.id.downloadButton);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                new DownloadDataTask().execute(url);
            }
        });
    }

    private class DownloadDataTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String data = "";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    data += line + "\n";
                }
                reader.close();

            } catch (Exception e) {
                e.printStackTrace();
                data = "Error: " + e.getMessage();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            dataTextView.setText(result);
        }
    }
}
