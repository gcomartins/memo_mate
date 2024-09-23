package com.example.memomate.data

import java.util.Date

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val birthDate: Date
)
