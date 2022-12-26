package com.dst.dailyjournal.eating.domain.repository

import com.dst.dailyjournal.eating.domain.model.Eating
import java.util.*

interface EatingRepository {
    suspend fun getEatingByDate(date: Date): Eating?

    suspend fun insertEating(eating: Eating)
}