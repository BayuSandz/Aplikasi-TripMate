package com.example.cobaapi2.view_model

import androidx.lifecycle.ViewModel
import com.example.cobaapi2.data.User
import com.example.cobaapi2.utils.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Response

class AuthViewModel : ViewModel() {

    private val apiService = RetrofitInstance.apiService

    suspend fun registerUser(nama: String, email: String, password: String): Response<ResponseBody> {
        val user = User(nama, email, password)
        return apiService.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): Response<ResponseBody> {
        val user = User("", email, password) // username tidak relevan untuk login
        return apiService.loginUser(user)
    }
}
