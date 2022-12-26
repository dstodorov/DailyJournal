package com.dst.dailyjournal.eating.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dst.dailyjournal.eating.domain.model.Eating
import java.util.*

@Dao
interface EatingDao {
    @Query("SELECT * FROM meals WHERE eatingDate = :date")
    suspend fun getEatingByDate(date: Date): Eating?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEating(training: Eating)
}