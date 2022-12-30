package com.dst.dailyjournal.ui.diary


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dst.dailyjournal.core.util.DateTools
import com.dst.dailyjournal.diary.domain.model.Note
import com.dst.dailyjournal.diary.domain.use_case.NoteUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val noteUsesCases: NoteUsesCases
) : ViewModel() {

    private val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    private val _currentDate = MutableLiveData<String>().apply {
        value = LocalDate.now().format(formatter)
    }

    private var diaryLoaded = false

    val currentDate: LiveData<String> = _currentDate

    private var date =
        SimpleDateFormat("dd MMM yyyy", Locale.US).parse(currentDate.value.toString())

    private var _currentDiary = MutableLiveData<Note>().apply {
        Note(
            noteDate = date!!
        )
    }

    val currentDiary: LiveData<Note> = _currentDiary

    fun loadCurrentDayDiary() {
        viewModelScope.launch {
            val todayDiary = noteUsesCases.getNoteByDate(date!!)

            if (todayDiary == null) {
                _currentDiary.value = Note(
                    noteDate = date
                )

                return@launch
            }

            _currentDiary.value = todayDiary
            diaryLoaded = true
        }
    }

    var diaryText: String? = null

    fun saveDiary() {
        currentDiary.value?.noteDate = date!!
        currentDiary.value?.text = diaryText

        viewModelScope.launch {
            noteUsesCases.addNote(currentDiary.value!!)
        }
    }

    fun setCurrentDate(date: Date) {
        val dateToString = DateTools.dateToString("dd MMM yyyy", date)
        this.date = SimpleDateFormat("dd MMM yyyy", Locale.US).parse(dateToString)
    }
}