package com.dst.dailyjournal.training.data.repository

import com.dst.dailyjournal.training.data.data_source.TrainingDao
import com.dst.dailyjournal.training.domain.model.Training
import com.dst.dailyjournal.training.domain.repository.TrainingRepository
import java.util.Date

class TrainingRepositoryImpl(
    private val dao: TrainingDao
) : TrainingRepository {
    override suspend fun getTrainingByDate(date: Date): Training? {
        return dao.getTrainingByDate(date)
    }

    override suspend fun insertTraining(training: Training) {
        dao.insertTraining(training)
    }
}