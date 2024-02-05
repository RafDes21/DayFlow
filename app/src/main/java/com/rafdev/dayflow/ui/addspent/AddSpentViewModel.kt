package com.rafdev.dayflow.ui.addspent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafdev.dayflow.data.db.enteties.SpentEntity
import com.rafdev.dayflow.domain.model.Note
import com.rafdev.dayflow.domain.usecase.InsertNoteUseCase
import com.rafdev.dayflow.domain.usecase.spent.InsertSpentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSpentViewModel @Inject constructor(private val insertSpentUseCase: InsertSpentUseCase) :
    ViewModel() {

    fun insertNewSpent(name: String, price: Float, category: String, description: String) {

        viewModelScope.launch {
            val newSpent = SpentEntity(name = name, price = price, category = category, description = description)
            insertSpentUseCase(newSpent)
        }
    }
}