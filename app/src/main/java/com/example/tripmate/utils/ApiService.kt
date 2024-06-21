package com.example.tripmate.utils

import com.example.tripmate.data.User
import com.example.tripmate.model.Place
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("tambahUser")
    suspend fun registerUser(@Body user: User): Response<ResponseBody>

    @POST("login")
    suspend fun loginUser(@Body user: User): Response<ResponseBody>
}