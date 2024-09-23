package com.example.memomate.ui.pages.signUp

import com.example.memomate.data.User
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

class SignUpFormData {
    companion object {
        var firstName: String? = null
        var lastName: String? = null
        var email: String? = null
        var password: String? = null
        var confirmPassword: String? = null
        var birthDate: String? = null

        fun validateForm(): ErrorMessages? {
            if (firstName.isNullOrEmpty()) {
                return ErrorMessages.ERROR_NAME
            }
            if (lastName.isNullOrEmpty()) {
                return ErrorMessages.ERROR_LAST_NAME
            }
            if (email.isNullOrEmpty()) {
                return ErrorMessages.ERROR_EMAIL
            }
            if (password.isNullOrEmpty()) {
                return ErrorMessages.ERROR_PASSWORD
            }

            if (confirmPassword.isNullOrEmpty()) {
                return ErrorMessages.ERROR_CONFIRM_PASSWORD
            }

            if (password != confirmPassword) {
                return ErrorMessages.ERROR_CONFIRM_PASSWORD2
            }

            if (birthDate.isNullOrEmpty()) {
                return ErrorMessages.ERROR_BIRTH_DATE
            }

            return null
        }

        fun getUserFromForm(): User {
            // Verifica se os campos não são nulos
            if (firstName.isNullOrBlank() || lastName.isNullOrBlank() || email.isNullOrBlank() || password.isNullOrBlank() || birthDate.isNullOrBlank()) {
                throw IllegalArgumentException("Todos os campos devem ser preenchidos.")
            }

            val birthDateLocal = stringToDate(birthDate!!)
            return User(firstName!!, lastName!!, email!!, password!!, birthDateLocal)
        }

        private fun stringToDate(dateString: String): Date {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val localDate = LocalDate.parse(dateString, formatter)
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        }

    }

    init {
        firstName = null
        lastName = null
        email = null
        password = null
        confirmPassword = null
    }


}
