package com.devs.faceRecognition.app

import android.app.Application
import androidx.room.Room
import com.devs.faceRecognition.data.Repository
import com.devs.faceRecognition.data.database.ListConverter
import com.devs.faceRecognition.data.database.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideListConverter(): ListConverter = ListConverter()

    @Provides
    @Singleton
    fun provideDatabase(app: Application, listConverter: ListConverter): MainDatabase =
        Room.databaseBuilder(app, MainDatabase::class.java, "MainDatabase").addTypeConverter(listConverter).build()

    @Provides
    @Singleton
    fun provideFaceRepo(app: Application, db: MainDatabase): Repository = Repository(app, db)

}