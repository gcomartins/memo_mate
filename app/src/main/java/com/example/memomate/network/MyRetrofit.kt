package com.example.memomate.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRetrofit {
    companion object {
        private var myRetrofit: Retrofit? = null
        private const val BASE_URL = "http://localhost:8080"

        fun getInstance(): Retrofit {
            if (myRetrofit == null) {
                val client = OkHttpClient.Builder()
                    .authenticator(MyAuthenticator("meminho", "senhazinha"))
                    .addInterceptor(MyInterceptor())
                    .build()

                myRetrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return myRetrofit!!
        }

    }


}