package com.example.memomate.data

import java.time.LocalDate

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val birthDate: LocalDate
)
