package com.rafdev.dayflow.ui.addtask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafdev.dayflow.domain.model.Note
import com.rafdev.dayflow.domain.usecase.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
) :
    ViewModel() {

    private val _dataIsValid = MutableLiveData<Boolean>()
    val dataIsValid: LiveData<Boolean> get() = _dataIsValid

    fun onFieldsChanged(title: String, description: String) {
        _dataIsValid.value = title.isNotEmpty() && description.isNotEmpty()

    }

    fun insertNewNote(title: String, description: String, hour: String, date: String) {

        viewModelScope.launch {
            val newNote = Note(title, description, hour, date)
            insertNoteUseCase(newNote)
        }
    }


}