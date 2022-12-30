package com.dst.dailyjournal.ui.home

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dst.dailyjournal.core.util.DateTools
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    private val _currentDate = MutableLiveData<String>().apply {
        value = LocalDate.now().format(formatter)
    }
    val currentDate: LiveData<String> = _currentDate

    var date = SimpleDateFormat("dd MMM yyyy", Locale.US).parse(currentDate.value.toString())


    private var _bundle = Bundle()
    val bundle: Bundle
        get() = _bundle


    fun setBundle(bundle: Bundle) {
        _bundle = bundle
    }

    fun setCurrentDate(date: Date) {
        val dateToString = DateTools.dateToString("dd MMM yyyy", date)
        this.date = SimpleDateFormat("dd MMM yyyy", Locale.US).parse(dateToString)
        this._currentDate.value = dateToString
    }
}