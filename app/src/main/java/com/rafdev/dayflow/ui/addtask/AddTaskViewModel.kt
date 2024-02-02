package com.rafdev.dayflow.ui.addtask

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafdev.dayflow.domain.model.Note
import com.rafdev.dayflow.domain.usecase.GetNotesUseCase
import com.rafdev.dayflow.domain.usecase.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase
) :
    ViewModel() {

    fun insertNewNote(description: String, hour: String) {

        viewModelScope.launch {
            // Insertar nueva nota
            val newNote = Note(description, hour)
            insertNoteUseCase(newNote)

            // Recuperar todas las notas e imprimir en el Logcat
            val allNotes =
                getNotesUseCase() // Asume que hay un caso de uso para recuperar todas las notas
            allNotes.forEach {
                Log.i("Note", "ID: ${it.id}, Description: ${it.description}, Hour: ${it.hour}")
            }
        }
    }
}