package com.adrorodri.composeexample.data.user.disk

import com.adrorodri.composeexample.data.user.model.User

class UserDisk {
    private var user: User? = null

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User) {
        this.user = user
    }
}