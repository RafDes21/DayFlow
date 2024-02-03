package com.rafdev.dayflow.ui.fragments.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import com.rafdev.dayflow.domain.usecase.DeleteNoteUseCase
import com.rafdev.dayflow.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {


    val isShow: LiveData<Boolean> = getNotesUseCase()
        .map { notesList ->
            notesList.isEmpty()
        }
        .asLiveData()

    val notes: LiveData<List<NoteEntity>> = getNotesUseCase().asLiveData()

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            deleteNoteUseCase(noteId)
        }
    }
}