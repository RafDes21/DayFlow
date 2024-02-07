package com.rafdev.dayflow.domain.usecase

import com.rafdev.dayflow.data.NoteRepository
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(private val repository: NoteRepository) {

    suspend operator fun invoke(id: Int): NoteEntity {
        return repository.getNoteById(id)
    }

}