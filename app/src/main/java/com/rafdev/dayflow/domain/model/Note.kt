package com.rafdev.dayflow.domain.model

import com.rafdev.dayflow.data.db.enteties.NoteEntity

data class Note(val description: String, val hour: String)

fun NoteEntity.toDomain() = Note(description, hour)
