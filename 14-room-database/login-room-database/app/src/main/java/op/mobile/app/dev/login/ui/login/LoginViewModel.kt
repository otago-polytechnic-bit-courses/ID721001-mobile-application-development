package op.mobile.app.dev.login.ui.login

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import op.mobile.app.dev.login.model.Login
import op.mobile.app.dev.login.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    val allLoginDetails: LiveData<List<Login>> = repository.allLoginDetails.asLiveData()

    fun insert(login: Login) = viewModelScope.launch {
        repository.insert(login)
    }
}
