package com.pra.retrofit_db.model.response

import com.google.gson.annotations.SerializedName
import com.pra.retrofit_db.model.user.User


data class UserResponseModel(

    @SerializedName("results")
    var users: List<User>
)