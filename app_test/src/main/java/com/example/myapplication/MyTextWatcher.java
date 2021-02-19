package com.example.myapplication;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 作者: zhangl
 * 时间: 2018/1/11
 * 描述: 输入监听
 **/

public abstract class MyTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
