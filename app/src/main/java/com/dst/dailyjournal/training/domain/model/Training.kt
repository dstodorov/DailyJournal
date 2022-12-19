package com.dst.dailyjournal.training.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "trainings")
data class Training(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val trainingDate: Date,
    val strengthTraining: String = "none",
    val cardioTraining: String = "none",
    val dailyStepsStatus: String?,
    val dailySteps: Int?
)