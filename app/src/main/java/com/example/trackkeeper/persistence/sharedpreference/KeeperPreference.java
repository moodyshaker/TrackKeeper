package com.example.trackkeeper.persistence.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class KeeperPreference {

    private static KeeperPreference sInstance;
    private SharedPreferences sharedPreferences;
    private final String preferenceName = "mySharedPreference";

    private KeeperPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    public static KeeperPreference getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new KeeperPreference(context);
        }
        return sInstance;
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public void clearAll() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static class UserPreferences {

        private static UserPreferences userPreferences;
        private KeeperPreference instance;
        private final String USER_ID = "user_id";
        private final String USER_NAME = "user_name";
        private final String USER_EMAIL = "user_email";
        private final String USER_IMAGE = "user_image";
        private final String USER_TYPE = "user_type";
        private final String DARK_MODE = "dark_mode";
        private final String FAVOURITE_KEY_INT = "favourite_key";
        private final String DONE_KEY_INT = "done_key";

        private UserPreferences(Context context) {
            instance = KeeperPreference.getInstance(context);
        }

        public static UserPreferences getUserPreferences(Context context) {
            if (userPreferences == null) {
                userPreferences = new UserPreferences(context);
            }
            return userPreferences;
        }

        public void setUserImage(String value) {
            instance.setString(USER_IMAGE, value);
        }

        public String getUserImage() {
            return instance.getString(USER_IMAGE);
        }

        public void setUserId(String value) {
            instance.setString(USER_ID, value);
        }

        public String getUserId() {
            return sInstance.getString(USER_ID);
        }

        public void setUserName(String value) {
            sInstance.setString(USER_NAME, value);
        }

        public String getUserName() {
            return sInstance.getString(USER_NAME);
        }

        public void setUserEmail(String value) {
            sInstance.setString(USER_EMAIL, value);
        }

        public String getUserEmail() {
            return sInstance.getString(USER_EMAIL);
        }

        public void setUserType(String value) {
            sInstance.setString(USER_TYPE, value);
        }

        public String getUserType() {
            return sInstance.getString(USER_TYPE);
        }

        public int getDarkMode() {
            return sInstance.getInt(DARK_MODE);
        }

        public void setDarkMode(int value) {
            sInstance.setInt(DARK_MODE, value);
        }

        public int getFavouriteInt() {
            return sInstance.getInt(FAVOURITE_KEY_INT);
        }

        public void setFavouriteInt(int value) {
            sInstance.setInt(FAVOURITE_KEY_INT, value);
        }

        public int getDoneInt() {
            return sInstance.getInt(DONE_KEY_INT);
        }

        public void setDoneInt(int value) {
            sInstance.setInt(DONE_KEY_INT, value);
        }

        public boolean isFirstTime() {
            if (getUserId().isEmpty()) {
                return true;
            } else {
                return false;
            }
        }

        public void logoutUser() {
            sInstance.clearAll();
        }
    }

}
