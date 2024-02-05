package com.rafdev.dayflow.di

import android.content.Context
import androidx.room.Room
import com.rafdev.dayflow.data.db.NoteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val NOTE_DATABASE_NAME = "note_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NoteDB::class.java, NOTE_DATABASE_NAME)
            .addMigrations(NoteDB.MIGRATION_1_2)
            .build()

    @Singleton
    @Provides
    fun provideNoteDao(db: NoteDB) = db.getNoteDao()

    @Singleton
    @Provides
    fun provide(db: NoteDB) = db.getSpentDao()

}