package com.agb24.mindfulwod.ui.animo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnimoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AnimoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}