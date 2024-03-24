package com.test.locksettingsconfiguration.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.locksettingsconfiguration.model.ParameterModel
import com.test.locksettingsconfiguration.repository.ParameterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParameterViewModel : ViewModel() {
    private val repository = ParameterRepository()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLockParameters()
        }
    }

    val parameters : LiveData<List<ParameterModel>>
        get() = repository.parameter
}