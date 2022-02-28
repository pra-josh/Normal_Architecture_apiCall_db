package com.pra.retrofit_db.model.user

import com.google.gson.annotations.SerializedName


data class Result(

    @SerializedName("cell")
    var cell: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("name")
    var name: Name,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("picture")
    var picture: Picture,

    )