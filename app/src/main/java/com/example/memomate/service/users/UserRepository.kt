package com.example.memomate.service.users

import com.example.memomate.data.User
import com.example.memomate.network.MyRetrofit

class UserRepository : UserService {
    override suspend fun getUsers(): List<User> {
        val service = createRetrofitUserService()
        return service.getUsers()
    }

    override suspend fun getUser(id: Int): User {
        val service = createRetrofitUserService()
        return service.getUser(id)
    }

    override suspend fun createUser(user: User): User {
        val service = createRetrofitUserService()
        return service.createUser(user)
    }

    override suspend fun updateUser(id: Int, user: User): User {
        val service = createRetrofitUserService()
        return service.updateUser(id, user)
    }

    override suspend fun deleteUser(id: Int) {
        val service = createRetrofitUserService()
        return service.deleteUser(id)
    }

    private fun createRetrofitUserService(): UserService {
        val myRetrofit = MyRetrofit.getInstance()
        return myRetrofit.create(UserService::class.java)
    }

}