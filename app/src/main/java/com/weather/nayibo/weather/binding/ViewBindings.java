package com.weather.nayibo.weather.binding;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class ViewBindings {

    private ViewBindings() {
        throw new AssertionError("No instances.");
    }

    @BindingAdapter("onClick")
    public static void onClick(View view, final ClickHandler clickHandler) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickHandler.onClick();
            }
        });
    }

    @BindingAdapter("isGone")
    public static void isGone(View view, Boolean isGone) {
        view.setVisibility(isGone ? View.GONE : View.VISIBLE);
    }

    @BindingAdapter("isInvisible")
    public static void isInvisible(View view, Boolean isInvisible) {
        view.setVisibility(isInvisible ? View.INVISIBLE : View.VISIBLE);
    }

    @BindingAdapter("watcher")
    public static void watcher(EditText view, final WatcherHandler watcherHandler) {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                watcherHandler.onChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @BindingAdapter("checkChange")
    public static void checkChange(CheckBox view, final CheckChangeHandler checkChangeHandler) {
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkChangeHandler.onChanged(isChecked);
            }
        });
    }

    public interface ClickHandler {
        void onClick();
    }

    public interface WatcherHandler {
        void onChanged(String text);
    }

    public interface CheckChangeHandler {
        void onChanged(boolean checked);
    }
}
