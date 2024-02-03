package com.rafdev.dayflow.domain.usecase

import com.rafdev.dayflow.data.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {

    suspend operator fun invoke(noteId: Int) {
        repository.deleteNoteById(noteId)
    }
}