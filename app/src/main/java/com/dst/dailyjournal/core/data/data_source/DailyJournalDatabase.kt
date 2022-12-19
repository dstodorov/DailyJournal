package com.dst.dailyjournal.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dst.dailyjournal.core.util.Converters
import com.dst.dailyjournal.training.data.data_source.TrainingDao
import com.dst.dailyjournal.training.domain.model.Training

@Database(
    entities = [Training::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DailyJournalDatabase : RoomDatabase() {
    abstract val trainingDao: TrainingDao

    companion object {
        const val DATABASE_NAME = "DailyJournalDB"
    }
}