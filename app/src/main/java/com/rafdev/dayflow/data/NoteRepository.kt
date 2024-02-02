package com.rafdev.dayflow.data

import com.rafdev.dayflow.data.db.dao.NoteDao
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import com.rafdev.dayflow.domain.model.Note
import com.rafdev.dayflow.domain.model.toEntity
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    suspend fun insertNote(note: Note) {
        noteDao.insert(note.toEntity())
    }


}