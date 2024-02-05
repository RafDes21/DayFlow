package com.rafdev.dayflow.data.db.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "hour") val hour: String
)

@Entity(tableName = "table_spent")
data class SpentEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "description") val description: String
)