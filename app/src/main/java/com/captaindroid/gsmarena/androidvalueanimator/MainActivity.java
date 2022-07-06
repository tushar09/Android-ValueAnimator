package com.captaindroid.gsmarena.androidvalueanimator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.captaindroid.gsmarena.androidvalueanimator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private float ballXpos;
    private float ballYpos;

    private int screenHeight;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ballXpos = binding.ball.getX();
                ballYpos = binding.ball.getY();

                ValueAnimator valueAnimatorY = ValueAnimator.ofFloat(ballYpos, screenHeight - binding.ball.getHeight() * 3);
                valueAnimatorY.setDuration(1000);
                valueAnimatorY.setInterpolator(new DecelerateInterpolator());
                valueAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        binding.ball.setY((Float) valueAnimator.getAnimatedValue());
                    }
                });

                ValueAnimator valueAnimatorX = ValueAnimator.ofFloat(ballXpos, screenWidth - binding.ball.getHeight() * 2);
                valueAnimatorX.setDuration(1000);
                valueAnimatorX.setInterpolator(new AccelerateInterpolator());
                valueAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        binding.ball.setX((Float) valueAnimator.getAnimatedValue());
                    }
                });
                valueAnimatorX.start();
                valueAnimatorY.start();
            }
        }, 1000);

    }
}