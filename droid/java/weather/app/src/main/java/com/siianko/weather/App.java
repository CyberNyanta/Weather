package com.siianko.weather;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by yevhenii.siianko on 6/16/17.
 */

public class App extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
