package com.dst.dailyjournal.diary.data.repository

import com.dst.dailyjournal.diary.data.data_source.NoteDao
import com.dst.dailyjournal.diary.domain.model.Note
import com.dst.dailyjournal.diary.domain.repository.NoteRepository
import java.util.*

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {
    override suspend fun getByNoteDate(noteDate: Date): Note? {
        return dao.getByNoteDate(noteDate)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }
}