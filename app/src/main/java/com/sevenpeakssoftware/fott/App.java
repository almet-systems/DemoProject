package com.sevenpeakssoftware.fott;

import android.app.Application;
import android.content.Context;


public class App extends Application {
    static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
