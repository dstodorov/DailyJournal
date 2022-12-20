package com.dst.dailyjournal.training.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "trainings")
data class Training(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var trainingDate: Date,
    var strengthTraining: StrengthTrainingState = StrengthTrainingState.NONE,
    var cardioTraining: CardioTrainingState = CardioTrainingState.NONE,
    var dailyStepsStatus: StepsState = StepsState.NOT_DONE,
    var dailySteps: Int?
)