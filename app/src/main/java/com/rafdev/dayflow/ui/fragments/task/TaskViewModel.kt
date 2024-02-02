package com.rafdev.dayflow.ui.fragments.task

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import com.rafdev.dayflow.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val getNotesUseCase: GetNotesUseCase) :
    ViewModel() {

    private val _notes = MutableLiveData<List<NoteEntity>>()
    val notes: LiveData<List<NoteEntity>> get() = _notes

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            val response = getNotesUseCase()
            _notes.value = response
            Log.i("Note", "$response")
        }
    }

}