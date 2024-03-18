package com.test.locksettingsconfiguration.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.locksettingsconfiguration.model.LockParameters
import com.test.locksettingsconfiguration.repository.ParameterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ParameterRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getParameter()
        }
    }

    val parameters : LiveData<Map<String, LockParameters>>
        get() = repository.parameter
}