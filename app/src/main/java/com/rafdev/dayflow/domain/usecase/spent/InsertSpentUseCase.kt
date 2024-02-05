package com.rafdev.dayflow.domain.usecase.spent

import com.rafdev.dayflow.data.NoteRepository
import com.rafdev.dayflow.data.db.enteties.SpentEntity
import javax.inject.Inject

class InsertSpentUseCase @Inject constructor(private val repository: NoteRepository) {

    suspend operator fun invoke(spent: SpentEntity){
        repository.insertSpent(spent)
    }
}