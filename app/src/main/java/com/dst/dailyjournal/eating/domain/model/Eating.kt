package com.dst.dailyjournal.eating.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "meals")
data class Eating(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var eatingDate: Date,
    var eatingState: EatingState = EatingState.NONE
)