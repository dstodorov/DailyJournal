package com.dst.dailyjournal.training.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dst.dailyjournal.training.domain.model.Training
import java.util.Date

@Dao
interface TrainingDao {

    @Query("SELECT * FROM trainings WHERE trainingDate = :date")
    suspend fun getTrainingByDate(date: Date): Training?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTraining(training: Training)
}