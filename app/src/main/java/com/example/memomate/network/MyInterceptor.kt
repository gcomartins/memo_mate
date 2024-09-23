package com.example.memomate.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Create the Basic Auth header
        val credential = Credentials.basic("meminho", "senhazinha")
        val request = chain.request().newBuilder().header("Authorization", credential).build()
        return chain.proceed(request)
    }
}