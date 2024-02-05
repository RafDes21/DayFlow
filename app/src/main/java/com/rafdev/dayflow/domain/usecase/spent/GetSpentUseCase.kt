package com.rafdev.dayflow.domain.usecase.spent

import com.rafdev.dayflow.data.NoteRepository
import com.rafdev.dayflow.data.db.enteties.SpentEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpentUseCase @Inject constructor(private val repository: NoteRepository) {

    operator fun invoke():Flow<List<SpentEntity>>{
        return repository.getAllSpent()
    }

}