package com.dst.dailyjournal.diary.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dst.dailyjournal.diary.domain.model.Note
import java.util.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes WHERE noteDate = :noteDate")
    suspend fun getByNoteDate(noteDate: Date): Note?

    @Insert
    suspend fun insertNote(note: Note)
}