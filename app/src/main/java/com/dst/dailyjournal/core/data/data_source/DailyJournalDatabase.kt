package com.dst.dailyjournal.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dst.dailyjournal.core.util.Converters
import com.dst.dailyjournal.eating.data.data_source.EatingDao
import com.dst.dailyjournal.eating.domain.model.Eating
import com.dst.dailyjournal.training.data.data_source.TrainingDao
import com.dst.dailyjournal.training.domain.model.Training

@Database(
    entities = [Training::class, Eating::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DailyJournalDatabase : RoomDatabase() {
    abstract val trainingDao: TrainingDao
    abstract val eatingDao: EatingDao

    companion object {
        const val DATABASE_NAME = "DailyJournalDB"
    }
}