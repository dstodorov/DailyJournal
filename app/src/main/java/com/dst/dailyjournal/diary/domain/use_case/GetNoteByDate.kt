package com.dst.dailyjournal.diary.domain.use_case

import com.dst.dailyjournal.diary.domain.model.Note
import com.dst.dailyjournal.diary.domain.repository.NoteRepository
import java.util.Date

class GetNoteByDate(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(noteDate: Date): Note? {
        return repository.getByNoteDate(noteDate)
    }
}