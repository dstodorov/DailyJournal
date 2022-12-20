package com.dst.dailyjournal.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dst.dailyjournal.training.domain.model.CardioTrainingState
import com.dst.dailyjournal.training.domain.model.StepsState
import com.dst.dailyjournal.training.domain.model.StrengthTrainingState
import com.dst.dailyjournal.training.domain.model.Training
import com.dst.dailyjournal.training.domain.use_case.TrainingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val trainingUseCases: TrainingUseCases
) : ViewModel() {

    private val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    private val _currentDate = MutableLiveData<String>().apply {
        value = LocalDate.now().format(formatter)
    }

    private var trainingLoaded = false

    val currentDate: LiveData<String> = _currentDate

    val date = SimpleDateFormat("dd MMM yyyy", Locale.US).parse(currentDate.value.toString())

    private var _currentTraining = MutableLiveData<Training>().apply {
        Training(
            0,
            trainingDate = date!!,
            dailySteps = null
        )
    }
    val currentTraining: LiveData<Training> = _currentTraining

    fun loadCurrentDayTraining() {
        viewModelScope.launch {
            val todayTraining = trainingUseCases.getTrainingByDate(date!!)

            if (todayTraining == null) {
                _currentTraining.value = Training(
                    0,
                    trainingDate = date,
                    dailySteps = 0
                )
                return@launch
            }

            _currentTraining.value = todayTraining
            trainingLoaded = true
        }
    }

    var strengthTrainingState: StrengthTrainingState = StrengthTrainingState.NONE
    var cardioTrainingState: CardioTrainingState = CardioTrainingState.NONE
    var dailyStepsStatus: StepsState = StepsState.NOT_DONE

    var dailyStepsCount: Int = 0


    fun saveTraining() {
        _currentTraining.value?.trainingDate = date!!
        _currentTraining.value?.cardioTraining = cardioTrainingState
        _currentTraining.value?.strengthTraining = strengthTrainingState
        _currentTraining.value?.dailyStepsStatus = dailyStepsStatus
        _currentTraining.value?.dailySteps = dailyStepsCount

        viewModelScope.launch {
            trainingUseCases.addTraining(currentTraining.value!!)
        }
    }

}