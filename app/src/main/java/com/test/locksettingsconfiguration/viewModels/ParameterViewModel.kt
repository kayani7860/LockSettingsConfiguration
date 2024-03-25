package com.test.locksettingsconfiguration.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.locksettingsconfiguration.model.Parameter
import com.test.locksettingsconfiguration.repository.ParameterRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParameterViewModel : ViewModel() {
    private val repository = ParameterRepository()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLockParameters()
        }
    }

    val parameters : LiveData<List<Parameter>>
        get() = repository.parameter
}