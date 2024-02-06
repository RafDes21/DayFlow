package com.rafdev.dayflow.ui.addtask

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

    fun insertNewNote(title: String, description: String, hour: String, date: String) {

        viewModelScope.launch {
            val newNote = Note(title, description, hour, date)
            insertNoteUseCase(newNote)
        }
    }
}