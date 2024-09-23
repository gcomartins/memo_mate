package com.example.memomate.service.users

import com.example.memomate.data.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("user")
    suspend fun getUsers(): List<User>

    @GET("user/{id}")
    suspend fun getUser(@Path("id") id: Int): User

    @POST("user/register")
    suspend fun createUser(@Body user: User): User

    @PUT("user/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): User

    @DELETE("user/{id}")
    suspend fun deleteUser(@Path("id") id: Int)
}