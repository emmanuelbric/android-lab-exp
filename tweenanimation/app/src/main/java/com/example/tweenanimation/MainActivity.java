package com.example.tweenanimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {
    private ObjectAnimator translateAnimator;
    private ObjectAnimator rotateAnimator;
    private ObjectAnimator scaleAnimator;
    private ObjectAnimator alphaAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BirdView birdView = findViewById(R.id.birdView);

        Switch translationSwitch = findViewById(R.id.translationSwitch);
        Switch rotationSwitch = findViewById(R.id.rotationSwitch);
        Switch scaleSwitch = findViewById(R.id.scaleSwitch);
        Switch alphaSwitch = findViewById(R.id.alphaSwitch);
        Button keyframeButton = findViewById(R.id.keyframeButton);

        // Toggle translation animation
        translationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startTranslationAnimation(birdView);
            } else {
                stopTranslationAnimation();
            }
        });

        // Toggle rotation animation
        rotationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startRotationAnimation(birdView);
            } else {
                stopRotationAnimation();
            }
        });

        // Toggle scale animation
        scaleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startScaleAnimation(birdView);
            } else {
                stopScaleAnimation();
            }
        });

        // Toggle alpha animation
        alphaSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startAlphaAnimation(birdView);
            } else {
                stopAlphaAnimation();
            }
        });

        // Navigate to Keyframe Animation Activity
        keyframeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BirdView.class);
            startActivity(intent);
        });
    }

    private void startTranslationAnimation(View view) {
        if (translateAnimator == null) {
            translateAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, 500f);
            translateAnimator.setDuration(1000);
            translateAnimator.setInterpolator(new LinearInterpolator());
            translateAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            translateAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            translateAnimator.start();
        }
    }

    private void stopTranslationAnimation() {
        if (translateAnimator != null) {
            translateAnimator.end();
            translateAnimator = null;
        }
    }

    private void startRotationAnimation(View view) {
        if (rotateAnimator == null) {
            rotateAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
            rotateAnimator.setDuration(1500);
            rotateAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            rotateAnimator.setRepeatMode(ObjectAnimator.RESTART);
            rotateAnimator.start();
        }
    }

    private void stopRotationAnimation() {
        if (rotateAnimator != null) {
            rotateAnimator.end();
            rotateAnimator = null;
        }
    }

    private void startScaleAnimation(View view) {
        if (scaleAnimator == null) {
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 2f);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 2f);
            scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY);
            scaleAnimator.setDuration(2000);
            scaleAnimator.setInterpolator(new BounceInterpolator());
            scaleAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            scaleAnimator.start();
        }
    }

    private void stopScaleAnimation() {
        if (scaleAnimator != null) {
            scaleAnimator.end();
            scaleAnimator = null;
        }
    }

    private void startAlphaAnimation(View view) {
        if (alphaAnimator == null) {
            alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            alphaAnimator.setDuration(1500);
            alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            alphaAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            alphaAnimator.start();
        }
    }

    private void stopAlphaAnimation() {
        if (alphaAnimator != null) {
            alphaAnimator.end();
            alphaAnimator = null;
        }
    }
}
