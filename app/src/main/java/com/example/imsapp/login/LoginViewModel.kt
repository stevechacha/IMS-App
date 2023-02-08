package com.example.imsapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.imsapp.utils.Event
import com.example.imsapp.utils.handleThrowable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class LoginViewModel : ViewModel() {
    private val _uiState: MutableLiveData<LoginUIState> = MutableLiveData(LoginUIState.Loading)
    val uiState: LiveData<LoginUIState> = _uiState

    private val _interactions = MutableLiveData<Event<LoginActions>>()
    val interactions: LiveData<Event<LoginActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(LoginUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }
}

sealed class LoginActions {
    data class Navigate(val destination: NavDirections) : LoginActions()
    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment) : LoginActions()
}

sealed class LoginUIState {

    object Loading : LoginUIState()

    data class Error(val message: String = "Try again") : LoginUIState()

    data class LimitError(val message: String) : LoginUIState()

    data class Message(val message: String) : LoginUIState()
}