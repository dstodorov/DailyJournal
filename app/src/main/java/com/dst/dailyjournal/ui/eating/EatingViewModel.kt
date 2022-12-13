package com.dst.dailyjournal.ui.eating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EatingViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is eating Fragment"
    }
    val text: LiveData<String> = _text
}