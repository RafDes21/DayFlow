package com.rafdev.dayflow.domain.model

import com.rafdev.dayflow.data.db.enteties.NoteEntity

data class Note(val title:String, val description: String, val hour: String, val date: String)

fun NoteEntity.toDomain() = Note(title,description, hour, date)
fun Note.toEntity() = NoteEntity(title = title, description = description, hour = hour, date = date)

