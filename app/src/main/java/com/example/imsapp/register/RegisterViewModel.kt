package com.example.imsapp.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.imsapp.utils.Event
import com.example.imsapp.utils.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableLiveData<SignUpUIState>()
    val uiState: LiveData<SignUpUIState> = _uiState

    private val _interactions = MutableLiveData<Event<SignUpActions>>()
    val interactions: LiveData<Event<SignUpActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(SignUpUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}


sealed class SignUpActions {
    data class Navigate(val destination: NavDirections) : SignUpActions()
}

sealed class SignUpUIState {

    object Loading : SignUpUIState()

    data class Error(val title: String = "Try again") : SignUpUIState()

    data class LimitError(val message: String) : SignUpUIState()
}