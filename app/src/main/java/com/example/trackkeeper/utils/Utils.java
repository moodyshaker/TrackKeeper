package com.example.trackkeeper.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import com.example.trackkeeper.R;
import com.example.trackkeeper.TrackKeeperApp;
import com.example.trackkeeper.persistence.sharedpreference.KeeperPreference;

public class Utils {
    static KeeperPreference.UserPreferences userPreferences = KeeperPreference.UserPreferences.getUserPreferences(TrackKeeperApp.getTrackKeeperApp().getApplicationContext());

    public static boolean checkImagePermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestImagePermission(Activity activity) {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(activity, permissions, AppConstants.IMAGE_REQUEST_PERMISSION);
    }

    public static void openGallery(Activity activity) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        i.setType("image/*");
        activity.startActivityForResult(i, AppConstants.IMAGE_GALLERY_REQUEST);
    }

    public static String getFilePath(Context context, Uri imageUri) {
        String filePath;
        Cursor cursor = context.getContentResolver()
                .query(imageUri,
                        null,
                        null,
                        null,
                        null);
        if (cursor == null) {
            filePath = imageUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }

    public static void checkDarkTheme(Context context) {
        if (userPreferences.getDarkMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            context.setTheme(R.style.DarkTheme);
        } else {
            context.setTheme(R.style.AppTheme);
        }
    }

    public static void setDarkTheme(Activity activity, int mode) {
        if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
            userPreferences.setDarkMode(mode);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            activity.recreate();
        } else {
            userPreferences.setDarkMode(mode);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            activity.recreate();
        }
    }
}
