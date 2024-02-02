package com.rafdev.dayflow.domain.usecase

import com.rafdev.dayflow.data.NoteRepository
import com.rafdev.dayflow.domain.model.Note
import javax.inject.Inject

class InsertNoteUseCase  @Inject constructor(private val repository: NoteRepository){

    suspend operator fun invoke(note: Note){
        repository.insertNote(note)
    }
}