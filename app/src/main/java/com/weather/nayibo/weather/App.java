package com.weather.nayibo.weather;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by nayibo on 2018/1/26.
 */

public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initFresco(this);
    }

    private void initFresco(Context context) {
        ImagePipelineConfig.newBuilder(context).setDownsampleEnabled(true).build();
        Fresco.initialize(context);
    }
}
