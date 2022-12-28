package com.dst.dailyjournal.diary.domain.repository

import com.dst.dailyjournal.diary.domain.model.Note
import java.util.Date

interface NoteRepository {
    suspend fun getByNoteDate(noteDate: Date): Note?

    suspend fun insertNote(note: Note)
}