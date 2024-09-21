package com.example.memomate.ui.pages.signUp

class SignUpForm {
    companion object {
        var name: String? = null
        var lastName: String? = null
        var email: String? = null
        var password: String? = null
        var confirmPassword: String? = null
        var birthDate: String? = null

        fun validateForm(): ErrorMessages? {

            if (name.isNullOrEmpty()) {
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
    }

    init {
        name = null
        lastName = null
        email = null
        password = null
        confirmPassword = null
    }


}
