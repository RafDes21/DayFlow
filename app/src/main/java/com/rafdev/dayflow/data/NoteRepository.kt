package com.rafdev.dayflow.data

import com.rafdev.dayflow.data.db.dao.NoteDao
import com.rafdev.dayflow.domain.model.Note
import com.rafdev.dayflow.domain.model.toDomain
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun getAllNotes(): List<Note> {
        val response = noteDao.getAllNotes()
        return response.map {it.toDomain()}
    }

//    suspend fun insertNote(note: Note) {
//        noteDao.insert(note)
//    }


}