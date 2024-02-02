package com.rafdev.dayflow.domain.usecase

import com.rafdev.dayflow.data.NoteRepository
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke():List<NoteEntity>{
        return repository.getAllNotes()
    }
}