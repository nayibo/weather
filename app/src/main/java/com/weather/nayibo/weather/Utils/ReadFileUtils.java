package com.weather.nayibo.weather.Utils;


import android.util.Log;

import com.weather.nayibo.weather.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by nayibo on 2018/2/8.
 */

public class ReadFileUtils implements ObservableOnSubscribe<String> {
    private String mFilePath;

    public ReadFileUtils(String path) {
        this.mFilePath = path;
    }

    @Override
    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
        try {
            FileInputStream input = new FileInputStream(mFilePath);
            InputStreamReader isr;
            BufferedReader br;

            isr = new InputStreamReader(input);
            br = new BufferedReader(isr);

            String str;
            while ((str = br.readLine()) != null) {
                emitter.onNext(str);
            }
            input.close();
            emitter.onComplete();
        } catch (Exception e) {
            emitter.onError(new Throwable(e.toString()));
        }
    }
}
