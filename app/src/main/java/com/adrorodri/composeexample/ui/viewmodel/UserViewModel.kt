package com.adrorodri.composeexample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    val userLoggedIn = MutableLiveData<Boolean>().apply { value = false }
    val userName = MutableLiveData<String?>().apply { value = null }
}