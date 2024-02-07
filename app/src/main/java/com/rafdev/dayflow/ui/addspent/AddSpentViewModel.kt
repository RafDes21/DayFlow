package com.rafdev.dayflow.ui.addspent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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


    private val _dataIsValid = MutableLiveData<Boolean>()
    val dataIsValid: LiveData<Boolean> get() = _dataIsValid

    fun onFieldsChanged(title: String, price: String, category: String, description: String) {
        _dataIsValid.value =
            title.isNotEmpty() && price.isNotEmpty() && category.isNotEmpty() && description.isNotEmpty()

    }

    fun insertNewSpent(
        name: String,
        price: Float,
        category: String,
        description: String,
        date: String
    ) {

        viewModelScope.launch {
            val newSpent = SpentEntity(
                name = name,
                price = price,
                category = category,
                description = description,
                date = date
            )
            insertSpentUseCase(newSpent)
        }
    }
}