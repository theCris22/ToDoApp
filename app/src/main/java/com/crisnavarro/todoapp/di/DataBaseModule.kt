package com.crisnavarro.todoapp.di

import android.content.Context
import androidx.room.Room
import com.crisnavarro.todoapp.core.Constants
import com.crisnavarro.todoapp.data.db.NotesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NotesDataBase::class.java, Constants.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNoteDao(dataBase: NotesDataBase) = dataBase.getNoteDao()

}