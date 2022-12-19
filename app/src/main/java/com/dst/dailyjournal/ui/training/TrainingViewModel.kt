package com.dst.dailyjournal.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dst.dailyjournal.training.domain.model.CardioTrainingState
import com.dst.dailyjournal.training.domain.model.StepsState
import com.dst.dailyjournal.training.domain.model.StrengthTrainingState
import com.dst.dailyjournal.training.domain.use_case.TrainingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val trainingUseCases: TrainingUseCases
): ViewModel() {

    private val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    private val _currentDate = MutableLiveData<String>().apply {
        value = LocalDateTime.now().format(formatter)
    }

    val currentDate: LiveData<String> = _currentDate

    var strengthTrainingState: StrengthTrainingState = StrengthTrainingState.NONE
    var cardioTrainingState: CardioTrainingState = CardioTrainingState.NONE
    var dailyStepsStatus: StepsState = StepsState.NOT_DONE


}