package com.weather.nayibo.weather.search;

import android.util.Log;

import com.weather.nayibo.weather.base.BaseViewModel;

/**
 * Created by nayibo on 2018/2/9.
 */

public class SearchViewModel extends BaseViewModel {
    public void textChanged(String text) {
        Log.d("nayibo", "text: " + text);
    }
}
