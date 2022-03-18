package com.pra.retrofit_db.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.pra.retrofit_db.db.AppDataBase

class MyApp : Application() {


    companion object {

        @Volatile
        private var INSTANCE: MyApp? = null

        fun getMyApp(): MyApp {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = MyApp()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private var db: AppDataBase? = null



    override fun onCreate() {
        super.onCreate()
    }


}