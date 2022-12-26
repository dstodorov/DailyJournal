package com.dst.dailyjournal.eating.domain.use_case

import com.dst.dailyjournal.eating.domain.model.Eating
import com.dst.dailyjournal.eating.domain.repository.EatingRepository

class AddEating(
    private val repository: EatingRepository
) {
    suspend operator fun invoke(eating: Eating) {
        repository.insertEating(eating)
    }
}