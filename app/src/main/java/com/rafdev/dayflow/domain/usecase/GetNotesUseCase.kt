package com.rafdev.dayflow.domain.usecase

import com.rafdev.dayflow.data.NoteRepository
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class GetNotesUseCase @Inject constructor(private val repository: NoteRepository) {
    operator fun invoke(): Flow<List<NoteEntity>> {
        return repository.getAllNotes()
    }
}