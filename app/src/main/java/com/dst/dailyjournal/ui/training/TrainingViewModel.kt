package com.dst.dailyjournal.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dst.dailyjournal.data.enums.StrengthTrainingType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TrainingViewModel : ViewModel() {

    private val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    private val _currentDate = MutableLiveData<String>().apply {
        value = LocalDateTime.now().format(formatter)
    }

    val currentDate: LiveData<String> = _currentDate

    private val _strengthTrainingStatus = MutableLiveData<StrengthTrainingType>()
    val strengthTrainingStatus: LiveData<StrengthTrainingType> = _strengthTrainingStatus


}