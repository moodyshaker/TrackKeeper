package com.example.trackkeeper.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackkeeper.R;
import com.example.trackkeeper.databinding.ActivitySplashBinding;
import com.example.trackkeeper.persistence.sharedpreference.KeeperPreference;
import com.example.trackkeeper.utils.Utils;

public class Splash extends AppCompatActivity {

    private Context context = this;
    private Activity activity = this;
    private ImageView applicationIcon;
    private TextView applicationName;
    private Animation topAnimation, bottomAnimation;
    private Handler handler;
    private KeeperPreference.UserPreferences userPreferences;
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.checkDarkTheme(context);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        userPreferences = KeeperPreference.UserPreferences.getUserPreferences(context);
        topAnimation = AnimationUtils.loadAnimation(activity, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(activity, R.anim.bottom_animation);
        applicationIcon.setAnimation(topAnimation);
        applicationName.setAnimation(bottomAnimation);
        handler = new Handler();
        handler.postDelayed(() -> {
            Intent i = new Intent(context, Home.class);
            startActivity(i);
            finish();
        }, 3000);
    }

    private void initUi() {
        applicationIcon = binding.applicationIcon;
        applicationName = binding.applicationName;
    }
}