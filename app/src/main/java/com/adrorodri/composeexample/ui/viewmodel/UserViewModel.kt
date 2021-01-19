package com.adrorodri.composeexample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adrorodri.composeexample.data.RepositoryProvider
import com.adrorodri.composeexample.data.user.model.User

class UserViewModel : ViewModel() {

    private val userRepository by lazy { RepositoryProvider.userRepository }

    val user = MutableLiveData<User?>()
        .apply { value = userRepository.getLoggedUser() }

    fun loginUser(username: String) {
        userRepository.loginUser(username = username).subscribe {
            this.user.value = userRepository.getLoggedUser()
        }
    }

    fun editUser(user: User?, onSuccess: (user: User?) -> Unit) {
        userRepository.editUser(newUser = user).subscribe {
            this.user.value = it
            onSuccess(it)
        }
    }
}