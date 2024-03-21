package com.test.locksettingsconfiguration.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.locksettingsconfiguration.model.LockConfig
import com.test.locksettingsconfiguration.repository.ParameterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val repository = ParameterRepository()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLockParameters()
        }
    }

    val parameters : LiveData<LockConfig?>
        get() = repository.parameter
}