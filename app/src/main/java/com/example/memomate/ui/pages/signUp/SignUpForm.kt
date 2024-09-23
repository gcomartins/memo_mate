package com.example.memomate.ui.pages.signUp

import com.example.memomate.data.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class SignUpForm {
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
            val birthDate = stringToLocalDate(birthDate!!)
            return User(firstName!!, lastName!!, email!!, password!!, birthDate)
        }

        private fun stringToLocalDate(dateString: String): LocalDate {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return LocalDate.parse(dateString, formatter)
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
