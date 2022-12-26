package com.dst.dailyjournal.ui.eating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dst.dailyjournal.eating.domain.model.Eating
import com.dst.dailyjournal.eating.domain.model.EatingState
import com.dst.dailyjournal.eating.domain.use_case.EatingUseCases
import com.dst.dailyjournal.training.domain.model.Training
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EatingViewModel @Inject constructor(
    private val eatingUseCases: EatingUseCases
) : ViewModel() {

    private val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    private val _currentDate = MutableLiveData<String>().apply {
        value = LocalDate.now().format(formatter)
    }

    private var eatingLoaded = false

    val currentDate: LiveData<String> = _currentDate

    val date = SimpleDateFormat("dd MMM yyyy", Locale.US).parse(currentDate.value.toString())

    private var _currentEating = MutableLiveData<Eating>().apply {
        Eating(
            0,
            eatingDate = date!!
        )
    }
    val currentEating: LiveData<Eating> = _currentEating


    fun loadCurrentDayEating() {
        viewModelScope.launch {
            val todayEating = eatingUseCases.getEatingByDate(date!!)

            if (todayEating == null) {
                _currentEating.value = Eating(
                    0,
                    eatingDate = date
                )
                return@launch
            }

            _currentEating.value = todayEating
            eatingLoaded = true
        }
    }

    var eatingState: EatingState = EatingState.NONE

    fun saveEating() {
        currentEating.value?.eatingDate = date!!
        currentEating.value?.eatingState = eatingState

        viewModelScope.launch {
            eatingUseCases.addEating(currentEating.value!!)
        }
    }
}
