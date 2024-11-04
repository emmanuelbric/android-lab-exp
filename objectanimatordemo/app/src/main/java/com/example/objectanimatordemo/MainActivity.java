package com.example.objectanimatordemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView animatedView;
    private Spinner interpolatorSpinner;
    private Interpolator[] interpolators;
    private String[] interpolatorNames = {
            "AccelerateDecelerateInterpolator",
            "AccelerateInterpolator",
            "AnticipateInterpolator",
            "AnticipateOvershootInterpolator",
            "BounceInterpolator",
            "CycleInterpolator",
            "DecelerateInterpolator",
            "LinearInterpolator",
            "OvershootInterpolator"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animatedView = findViewById(R.id.animatedView);
        interpolatorSpinner = findViewById(R.id.interpolatorSpinner);

        // Initialize interpolators
        interpolators = new Interpolator[]{
                new AccelerateDecelerateInterpolator(),
                new AccelerateInterpolator(),
                new AnticipateInterpolator(),
                new AnticipateOvershootInterpolator(),
                new BounceInterpolator(),
                new CycleInterpolator(1), // you can change the number of cycles
                new DecelerateInterpolator(),
                new LinearInterpolator(),
                new OvershootInterpolator()
        };

        // Set up Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                interpolatorNames
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interpolatorSpinner.setAdapter(adapter);

        interpolatorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                animateView(interpolators[position]);
                Toast.makeText(MainActivity.this, "Current Interpolator: " + interpolatorNames[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void animateView(Interpolator interpolator) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(animatedView, "scaleX", 0f, 1f);
        scaleXAnimator.setDuration(1000); // 1 second duration
        scaleXAnimator.setInterpolator(interpolator);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(animatedView, "scaleY", 0f, 1f);
        scaleYAnimator.setDuration(1000); // 1 second duration
        scaleYAnimator.setInterpolator(interpolator);

        // Play animations together
        scaleXAnimator.start();
        scaleYAnimator.start();
    }
}
