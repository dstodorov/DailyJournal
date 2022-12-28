package com.dst.dailyjournal.diary.domain.use_case

import com.dst.dailyjournal.diary.domain.model.Note
import com.dst.dailyjournal.diary.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.insertNote(note)
    }
}