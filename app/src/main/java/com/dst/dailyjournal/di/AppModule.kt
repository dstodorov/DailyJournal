package com.dst.dailyjournal.di

import android.app.Application
import android.provider.DocumentsContract.Root
import androidx.room.Room
import com.dst.dailyjournal.core.data.data_source.DailyJournalDatabase
import com.dst.dailyjournal.training.data.repository.TrainingRepositoryImpl
import com.dst.dailyjournal.training.domain.repository.TrainingRepository
import com.dst.dailyjournal.training.domain.use_case.AddTraining
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
            AddTraining(repository)
        )
    }
}