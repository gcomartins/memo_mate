package com.example.memomate.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class MyAuthenticator: Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val newToken = getNewToken()

        return response.request().newBuilder()
            .header("Authorization", "Bearer $newToken")
            .build()
    }

    private fun getNewToken(): String {
        return "novo_token"
    }
}