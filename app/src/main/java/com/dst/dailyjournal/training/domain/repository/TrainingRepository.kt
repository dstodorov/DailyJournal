package com.dst.dailyjournal.training.domain.repository

import com.dst.dailyjournal.training.domain.model.Training
import java.util.*

interface TrainingRepository {
    suspend fun getTrainingByDate(date: Date): Training?

    suspend fun insertTraining(training: Training)
}