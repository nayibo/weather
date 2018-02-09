package com.weather.nayibo.weather.search;

import com.weather.nayibo.weather.BR;
import com.weather.nayibo.weather.R;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by nayibo on 2018/2/9.
 */

public class SearchSuggestItemBinding implements OnItemBind<SearchSuggestItemViewModel> {
    private static SearchSuggestItemBinding INSTANCE;

    @Override
    public void onItemBind(ItemBinding itemBinding, int position, SearchSuggestItemViewModel item) {
        itemBinding.set(BR.vm, R.layout.search_suggest_item);
    }

    public static SearchSuggestItemBinding get() {
        if (INSTANCE == null) {
            INSTANCE = new SearchSuggestItemBinding();
        }
        return INSTANCE;
    }
}
