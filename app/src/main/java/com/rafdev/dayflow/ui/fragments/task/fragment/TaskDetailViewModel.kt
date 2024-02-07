package com.rafdev.dayflow.ui.fragments.task.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import com.rafdev.dayflow.domain.usecase.DeleteNoteUseCase
import com.rafdev.dayflow.domain.usecase.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase, private val deleteNoteUseCase: DeleteNoteUseCase
) :
    ViewModel() {

    val result = MutableLiveData<NoteEntity?>()
    fun getNote(id: Int) {
        viewModelScope.launch {
            result.value = getNoteUseCase(id)

        }
    }

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            deleteNoteUseCase(noteId)
        }
    }

}