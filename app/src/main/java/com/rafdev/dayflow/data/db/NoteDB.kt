package com.rafdev.dayflow.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafdev.dayflow.data.db.dao.NoteDao
import com.rafdev.dayflow.data.db.enteties.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDB : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}