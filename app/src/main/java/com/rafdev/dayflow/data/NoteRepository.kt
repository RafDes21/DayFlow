package com.rafdev.dayflow.data

import com.rafdev.dayflow.data.db.dao.NoteDao
import com.rafdev.dayflow.data.db.dao.SpentDao
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import com.rafdev.dayflow.data.db.enteties.SpentEntity
import com.rafdev.dayflow.domain.model.Note
import com.rafdev.dayflow.domain.model.toEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class NoteRepository @Inject constructor(private val noteDao: NoteDao, private val spentDao: SpentDao) {
    fun getAllNotes(): Flow<List<NoteEntity>> {
        return noteDao.getAllNotes()
    }

    suspend fun insertNote(note: Note) {
        noteDao.insert(note.toEntity())
    }

    suspend fun deleteNoteById(noteId: Int) {
        noteDao.deleteById(noteId)
    }

    fun getAllSpent():Flow<List<SpentEntity>>{
        return spentDao.getSpent()
    }

    suspend fun insertSpent(spent: SpentEntity){
        spentDao.insert(spent)
    }

    suspend fun deleteSpentById(spentId: Int){
        spentDao.deleteById(spentId)
    }




}