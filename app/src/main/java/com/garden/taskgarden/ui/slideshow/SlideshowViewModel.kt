package com.garden.taskgarden.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {
    private val mText: MutableLiveData<String?> = MutableLiveData()
    val text: LiveData<String?>
        get() = mText

    init {
        mText.value = "This is slideshow fragment"
    }
}