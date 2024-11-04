package com.example.bitmaptext;


import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Button createBitmapButton;
    private ImageView bitmapImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        createBitmapButton = findViewById(R.id.createBitmapButton);
        bitmapImageView = findViewById(R.id.bitmapImageView);

        createBitmapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText
                String text = inputEditText.getText().toString();

                // Create the bitmap with the text
                Bitmap bitmap = createBitmapWithText(text);

                // Display the bitmap in the ImageView
                bitmapImageView.setImageBitmap(bitmap);
            }
        });
    }

    private Bitmap createBitmapWithText(String text) {
        // Define the width and height of the bitmap
        int width = 500;
        int height = 200;

        // Create a bitmap with the defined width and height
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // Create a canvas to draw on the bitmap
        Canvas canvas = new Canvas(bitmap);

        // Define the paint to use for drawing the text
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        paint.setTextAlign(Paint.Align.CENTER);

        // Draw the text on the canvas
        canvas.drawText(text, width / 2, height / 2, paint);

        return bitmap;
    }
}
