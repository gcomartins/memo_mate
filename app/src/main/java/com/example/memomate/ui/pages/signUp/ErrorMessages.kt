package com.example.memomate.ui.pages.signUp

enum class ErrorMessages(val message: String) {
    ERROR_NAME("Name is required"),
    ERROR_LAST_NAME("Last name is required"),
    ERROR_EMAIL("Email is required"),
    ERROR_PASSWORD("Password is required"),
    ERROR_CONFIRM_PASSWORD("Confirm password is required"),
    ERROR_CONFIRM_PASSWORD2("Passwords do not match"),
    ERROR_BIRTH_DATE("Birth date is required")
}