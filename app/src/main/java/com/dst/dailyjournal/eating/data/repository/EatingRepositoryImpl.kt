package com.dst.dailyjournal.eating.data.repository

import com.dst.dailyjournal.eating.data.data_source.EatingDao
import com.dst.dailyjournal.eating.domain.model.Eating
import com.dst.dailyjournal.eating.domain.repository.EatingRepository
import java.util.*

class EatingRepositoryImpl(
    private val dao: EatingDao
) : EatingRepository {
    override suspend fun getEatingByDate(date: Date): Eating? {
        return dao.getEatingByDate(date)
    }

    override suspend fun insertEating(eating: Eating) {
        dao.insertEating(eating)
    }
}