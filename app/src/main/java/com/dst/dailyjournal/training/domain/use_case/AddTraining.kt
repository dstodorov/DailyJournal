package com.dst.dailyjournal.training.domain.use_case

import com.dst.dailyjournal.training.domain.model.Training
import com.dst.dailyjournal.training.domain.repository.TrainingRepository

class AddTraining(
    private val repository: TrainingRepository
) {
    suspend operator fun invoke(training: Training) {
        repository.insertTraining(training)
    }
}