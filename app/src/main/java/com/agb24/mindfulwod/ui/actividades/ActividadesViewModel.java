package com.agb24.mindfulwod.ui.actividades;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActividadesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ActividadesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}