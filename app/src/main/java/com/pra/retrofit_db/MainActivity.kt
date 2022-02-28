package com.pra.retrofit_db

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pra.modernappmvvm.Data.api.WebApiClient
import com.pra.retrofit_db.adapter.UserAdapter
import com.pra.retrofit_db.databinding.ActivityMainBinding
import com.pra.retrofit_db.model.response.UserResponseModel
import com.pra.retrofit_db.model.user.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private var mUserAdapter: UserAdapter? = null

    private var name = arrayOf(
        "Marilyn Elliott", "Fernando Meyer", "Olive Walker", "Sébastien Nicolas",
        "Ronald Lynch", "Clarisse Pierre", "Felix Jørgensen", "Connie Bishop"
    )

    private var city = arrayOf(
        "Augusta", "Wolverhampton", "New Plymouth", "Roggliswil",
        "Orange", "Orléans", "Jerslev Sj", "Yakima"
    )

    private var picture = arrayOf(
        "https://randomuser.me/api/portraits/women/36.jpg",
        "https://randomuser.me/api/portraits/men/58.jpg",
        "https://randomuser.me/api/portraits/women/12.jpg",
        "https://randomuser.me/api/portraits/men/34.jpg",
        "https://randomuser.me/api/portraits/men/5.jpg",
        "https://randomuser.me/api/portraits/women/13.jpg",
        "https://randomuser.me/api/portraits/men/21.jpg",
        "https://randomuser.me/api/portraits/women/15.jpg"
    )

    private var mListUser: MutableList<Result> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        getUserAPiCalling()
        //  getData()
        //    mUserAdapter = UserAdapter(mListUser, this)
        //   mBinding.rvUsers.adapter = mUserAdapter
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter() {
        if (mUserAdapter == null) {
            mUserAdapter = UserAdapter(mListUser, this)
            mBinding.rvUsers.adapter = mUserAdapter
        } else {
            mUserAdapter?.notifyDataSetChanged()
        }
    }

    private fun getUserAPiCalling() {
        val mResponse: Call<UserResponseModel> = WebApiClient().getRandomUser()
        mResponse.enqueue(object : Callback<UserResponseModel> {
            override fun onResponse(
                call: Call<UserResponseModel>,
                response: Response<UserResponseModel>
            ) {
                when {
                    response.isSuccessful -> {
                        if (response.body() != null) {
                            val randomUserApiResponse = response.body()
                            mListUser.clear()
                            mListUser.addAll(randomUserApiResponse?.results!!)
                            setAdapter()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "" + response.errorBody().toString(),
                                Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                    response.errorBody() != null ->
                        Toast.makeText(
                            this@MainActivity,
                            "" + response.errorBody().toString(),
                            Toast.LENGTH_SHORT
                        ).show();
                    else ->
                        Toast.makeText(
                            this@MainActivity,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show();
                }
            }

            override fun onFailure(call: Call<UserResponseModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "" + t.message, Toast.LENGTH_SHORT).show();
            }
        })
    }


}