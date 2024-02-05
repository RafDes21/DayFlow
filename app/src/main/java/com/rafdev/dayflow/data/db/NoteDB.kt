package com.rafdev.dayflow.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rafdev.dayflow.data.db.dao.NoteDao
import com.rafdev.dayflow.data.db.dao.SpentDao
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import com.rafdev.dayflow.data.db.enteties.SpentEntity

@Database(entities = [NoteEntity::class, SpentEntity::class], version = 2)
abstract class NoteDB : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract fun getSpentDao(): SpentDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS table_spent (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                            "name TEXT NOT NULL," +
                            "price REAL NOT NULL," +
                            "category TEXT NOT NULL," +
                            "description TEXT NOT NULL)"
                )
            }
        }
    }
}