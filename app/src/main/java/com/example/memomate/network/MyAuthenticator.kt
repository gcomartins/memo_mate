package com.example.memomate.network

import okhttp3.Authenticator
import okhttp3.Credentials
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class MyAuthenticator(private val username: String, private val password: String) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // Check if the request has already been attempted
        if (response.request().header("Authorization") != null) {
            return null // If we already tried to authenticate, return null to avoid endless loop
        }

        // Create the Basic Auth header
        val credential = Credentials.basic(username, password)

        return response.request().newBuilder()
            .header("Authorization", credential)
            .build()
    }
}
