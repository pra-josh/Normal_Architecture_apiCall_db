package com.pra.retrofit_db.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserId")
    var id:Int? = null,

    @SerializedName("cell")
    @ColumnInfo
    var cell: String,

    @SerializedName("email")
    @ColumnInfo
    var email: String,

    @SerializedName("gender")
    @ColumnInfo
    var gender: String,

    /*@SerializedName("name")
    @ColumnInfo
    var name: Name,
*/
    @SerializedName("phone")
    @ColumnInfo
    var phone: String,

   /* @SerializedName("picture")
    @ColumnInfo
    var picture: Picture,
*/

    )