package com.pra.modernappmvvm.Data.api

import com.pra.retrofit_db.model.response.UserResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface WebApi {

   // https://randomuser.me/
    @GET("api/?results=5&inc=name,email,phone,cell,picture,gender&gender=female")
    fun getRandomUser(): Call<UserResponseModel>

}