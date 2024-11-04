package com.example.bitmapimage;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView bitmapImageView;
    private Button showImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bitmapImageView = findViewById(R.id.bitmapImageView);
        showImageButton = findViewById(R.id.showImageButton);

        showImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load the bitmap from resources
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bit_images);

                // Display the bitmap in the ImageView
                bitmapImageView.setImageBitmap(bitmap);
                bitmapImageView.setVisibility(View.VISIBLE);
            }
        });
    }
}
