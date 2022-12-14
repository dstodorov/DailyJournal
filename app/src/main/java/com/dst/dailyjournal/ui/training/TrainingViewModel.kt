package com.dst.dailyjournal.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TrainingViewModel : ViewModel() {

    private val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    private val _currentDate = MutableLiveData<String>().apply {
        value = LocalDateTime.now().format(formatter)
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is training Fragment"
    }
    val text: LiveData<String> = _text
    val currentDate: LiveData<String> = _currentDate
}