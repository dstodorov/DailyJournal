package com.dst.dailyjournal.eating.domain.use_case

import com.dst.dailyjournal.eating.domain.model.Eating
import com.dst.dailyjournal.eating.domain.repository.EatingRepository
import java.util.Date

class GetEatingByDate(
    private val repository: EatingRepository
) {
    suspend operator fun invoke(date: Date): Eating? {
        return repository.getEatingByDate(date)
    }
}