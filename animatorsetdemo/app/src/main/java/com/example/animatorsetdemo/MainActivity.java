package com.example.animatorsetdemo;

import android.animation.AnimatorSet;
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

    private TextView animatedView1;
    private TextView animatedView2;
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

        animatedView1 = findViewById(R.id.animatedView1);
        animatedView2 = findViewById(R.id.animatedView2);
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
                animateViews(interpolators[position]);
                Toast.makeText(MainActivity.this, "Current Interpolator: " + interpolatorNames[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void animateViews(Interpolator interpolator) {
        // Create ObjectAnimators for animatedView1
        ObjectAnimator scaleXAnimator1 = ObjectAnimator.ofFloat(animatedView1, "scaleX", 0f, 1f);
        ObjectAnimator scaleYAnimator1 = ObjectAnimator.ofFloat(animatedView1, "scaleY", 0f, 1f);
        scaleXAnimator1.setDuration(1000);
        scaleYAnimator1.setDuration(1000);
        scaleXAnimator1.setInterpolator(interpolator);
        scaleYAnimator1.setInterpolator(interpolator);

        // Create ObjectAnimators for animatedView2
        ObjectAnimator scaleXAnimator2 = ObjectAnimator.ofFloat(animatedView2, "scaleX", 0f, 1f);
        ObjectAnimator scaleYAnimator2 = ObjectAnimator.ofFloat(animatedView2, "scaleY", 0f, 1f);
        scaleXAnimator2.setDuration(1000);
        scaleYAnimator2.setDuration(1000);
        scaleXAnimator2.setInterpolator(interpolator);
        scaleYAnimator2.setInterpolator(interpolator);

        // Create an AnimatorSet to group the animators
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator1, scaleYAnimator1, scaleXAnimator2, scaleYAnimator2);

        // Start the animation
        animatorSet.start();
    }
}
