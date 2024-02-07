package com.rafdev.dayflow.ui.fragments.spent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rafdev.dayflow.data.db.enteties.SpentEntity
import com.rafdev.dayflow.domain.usecase.spent.DeleteSpentUseCase
import com.rafdev.dayflow.domain.usecase.spent.GetSpentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpentViewModel @Inject constructor(
    getSpentUseCase: GetSpentUseCase,
    val deleteSpentUseCase: DeleteSpentUseCase
) :
    ViewModel() {

    val isShow: LiveData<Boolean> = getSpentUseCase()
        .map { spentList ->
            spentList.isEmpty()
        }
        .asLiveData()

    val spent: LiveData<List<SpentEntity>> = getSpentUseCase().asLiveData()

    fun deleteSpent(noteId: Int) {
        viewModelScope.launch {
            deleteSpentUseCase(noteId)
        }
    }

}