package com.rafdev.dayflow.domain.usecase.spent

import com.rafdev.dayflow.data.NoteRepository
import javax.inject.Inject

class DeleteSpentUseCase @Inject constructor(private val repository: NoteRepository) {

    suspend operator fun invoke(spentId: Int) {
        repository.deleteSpentById(spentId)
    }
}