package com.rafdev.dayflow.ui.fragments.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.rafdev.dayflow.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(private val getNotesUseCase: GetNotesUseCase) : ViewModel() {

    val notes = liveData {
        emitSource(getNotesUseCase().asLiveData())
    }
}