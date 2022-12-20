package com.dst.dailyjournal.training.domain.use_case

import com.dst.dailyjournal.training.domain.model.Training
import com.dst.dailyjournal.training.domain.repository.TrainingRepository
import java.util.*

class GetTrainingByDate(
    private val repository: TrainingRepository
) {
    suspend operator fun invoke(date: Date): Training? {
        return repository.getTrainingByDate(date)
    }
}