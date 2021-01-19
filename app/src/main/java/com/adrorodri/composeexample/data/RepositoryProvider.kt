package com.adrorodri.composeexample.data

import com.adrorodri.composeexample.data.user.UserRepository
import com.adrorodri.composeexample.data.user.disk.UserDisk
import com.adrorodri.composeexample.data.user.network.UserNetwork

object RepositoryProvider {
    private val userNetwork by lazy { UserNetwork() }
    private val userDisk by lazy { UserDisk() }
    val userRepository by lazy { UserRepository(userNetwork = userNetwork, userDisk = userDisk) }
}