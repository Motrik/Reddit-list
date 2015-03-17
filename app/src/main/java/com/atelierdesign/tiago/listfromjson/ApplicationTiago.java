package com.atelierdesign.tiago.listfromjson;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Tiago on 12-03-2015.
 */
public class ApplicationTiago extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

    }
}
