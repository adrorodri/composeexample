package com.adrorodri.composeexample.data.user

import com.adrorodri.composeexample.data.user.disk.UserDisk
import com.adrorodri.composeexample.data.user.model.User
import com.adrorodri.composeexample.data.user.network.UserNetwork
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.just

class UserRepository(val userNetwork: UserNetwork, val userDisk: UserDisk) {
    fun loginUser(username: String): Observable<User?> {
        userDisk.setUser(
            User(
                username = username,
                firstName = "FN",
                lastName = "LN"
            )
        )
        return just(userDisk.getUser())
    }

    fun isUserLoggedIn(): Boolean {
        return userDisk.getUser() != null
    }

    fun getLoggedUser(): User? {
        return userDisk.getUser()
    }

    fun editUser(newUser: User?): Observable<User?> {
        if (newUser == null)
            return just(null)
        userDisk.setUser(newUser)
        return just(userDisk.getUser())
    }
}