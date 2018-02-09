package com.weather.nayibo.weather.search;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.text.TextUtils;

import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.utils.Constant;

/**
 * Created by nayibo on 2018/2/9.
 */

public class SearchViewModel extends BaseViewModel {
    public final ObservableField<Boolean> suggestVisibility = new ObservableField<>();
    private final ObservableList<SearchSuggestItemViewModel> mData = new ObservableArrayList<>();

    public SearchViewModel() {
        suggestVisibility.set(false);
    }

    public void textChanged(String text) {
        if (TextUtils.isEmpty(text)) {
            suggestVisibility.set(true);
        } else {
            suggestVisibility.set(false);
            mData.clear();
            mData.add(new SearchSuggestItemViewModel(Constant.getCityBeans().get(text.length())));
        }
    }

    public ObservableList<SearchSuggestItemViewModel> items() {
        return mData;
    }

    public void onItemBound(int position) {

    }
}
