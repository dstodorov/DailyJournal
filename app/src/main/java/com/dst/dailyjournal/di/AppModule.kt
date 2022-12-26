package com.dst.dailyjournal.di

import android.app.Application
import androidx.room.Room
import com.dst.dailyjournal.core.data.data_source.DailyJournalDatabase
import com.dst.dailyjournal.eating.data.repository.EatingRepositoryImpl
import com.dst.dailyjournal.eating.domain.repository.EatingRepository
import com.dst.dailyjournal.eating.domain.use_case.AddEating
import com.dst.dailyjournal.eating.domain.use_case.EatingUseCases
import com.dst.dailyjournal.eating.domain.use_case.GetEatingByDate
import com.dst.dailyjournal.training.data.repository.TrainingRepositoryImpl
import com.dst.dailyjournal.training.domain.repository.TrainingRepository
import com.dst.dailyjournal.training.domain.use_case.AddTraining
import com.dst.dailyjournal.training.domain.use_case.GetTrainingByDate
import com.dst.dailyjournal.training.domain.use_case.TrainingUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDailyJournalDatabase(app: Application): DailyJournalDatabase {
        return Room.databaseBuilder(
            app,
            DailyJournalDatabase::class.java,
            DailyJournalDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrainingRepository(database: DailyJournalDatabase): TrainingRepository {
        return TrainingRepositoryImpl(database.trainingDao)
    }

    @Provides
    @Singleton
    fun provideTrainingUseCases(repository: TrainingRepository): TrainingUseCases {
        return TrainingUseCases(
            AddTraining(repository),
            GetTrainingByDate(repository)
        )
    }

    @Provides
    @Singleton
    fun provideEatingRepository(database: DailyJournalDatabase): EatingRepository {
        return EatingRepositoryImpl(database.eatingDao)
    }

    @Provides
    @Singleton
    fun provideEatingUseCases(repository: EatingRepository): EatingUseCases {
        return EatingUseCases(
            AddEating(repository),
            GetEatingByDate(repository)
        )
    }
}