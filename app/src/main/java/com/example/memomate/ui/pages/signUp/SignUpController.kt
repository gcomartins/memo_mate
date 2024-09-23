package com.example.memomate.ui.pages.signUp

import com.example.memomate.data.User
import com.example.memomate.service.users.UserRepository

class SignUpController(private val userRepository: UserRepository) {

    suspend fun signUpUser(user: User): Result<User> {
        return try {
            val createdUser = userRepository.createUser(user)
            Result.success(createdUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}