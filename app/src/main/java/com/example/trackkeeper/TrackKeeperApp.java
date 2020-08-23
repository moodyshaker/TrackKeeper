package com.example.trackkeeper;

import android.app.Application;

public class TrackKeeperApp extends Application {
    private static TrackKeeperApp app;

    public static TrackKeeperApp getTrackKeeperApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
