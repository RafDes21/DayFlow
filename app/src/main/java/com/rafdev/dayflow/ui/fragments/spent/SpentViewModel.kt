package com.rafdev.dayflow.ui.fragments.spent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rafdev.dayflow.data.db.enteties.SpentEntity
import com.rafdev.dayflow.domain.usecase.spent.GetSpentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpentViewModel @Inject constructor(getSpentUseCase: GetSpentUseCase) :
    ViewModel() {

    val spent: LiveData<List<SpentEntity>> = getSpentUseCase().asLiveData()

}