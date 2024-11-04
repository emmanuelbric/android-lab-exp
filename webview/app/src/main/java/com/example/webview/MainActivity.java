package com.example.webview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText urlEditText;
    private TextView dataTextView;
    private Button downloadButton;
    private Button webViewButton; // Button to load webpage
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = findViewById(R.id.urlEditText);
        dataTextView = findViewById(R.id.dataTextView);
        downloadButton = findViewById(R.id.downloadButton);
        webViewButton = findViewById(R.id.webViewButton); // Initialize WebView button
        webView = findViewById(R.id.webView);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                if (!url.isEmpty()) {
                    new DownloadDataTask().execute(url);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up the WebView button to load a webpage
        webViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWebPage();
            }
        });

        // Set up WebView client to handle page redirects within the app
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(MainActivity.this, "Error: " + description, Toast.LENGTH_SHORT).show();
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript
    }

    private void loadWebPage() {
        String url = urlEditText.getText().toString();
        if (!url.isEmpty()) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                webView.loadUrl(url);
            } else {
                Toast.makeText(this, "Invalid URL. Please enter a URL starting with http:// or https://", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a valid URL", Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadDataTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String data = "";
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    data += line + "\n";
                }

            } catch (Exception e) {
                e.printStackTrace();
                data = "Error: " + e.getMessage();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            dataTextView.setText(result);
        }
    }
}
