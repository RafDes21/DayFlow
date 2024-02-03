package com.rafdev.dayflow.ui.fragments.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rafdev.dayflow.domain.usecase.DeleteNoteUseCase
import com.rafdev.dayflow.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    val notes = liveData {
        emitSource(getNotesUseCase().asLiveData())
    }

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            deleteNoteUseCase(noteId)
        }
    }

}