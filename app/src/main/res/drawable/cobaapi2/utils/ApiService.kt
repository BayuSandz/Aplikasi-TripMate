package com.example.cobaapi2.utils

import com.example.cobaapi2.data.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("tambahUser")
    suspend fun registerUser(@Body user: User): Response<ResponseBody>

    @POST("login")
    suspend fun loginUser(@Body user: User): Response<ResponseBody>
}

