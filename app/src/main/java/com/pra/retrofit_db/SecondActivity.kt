package com.pra.retrofit_db

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.pra.modernappmvvm.Data.api.WebApiClient
import com.pra.retrofit_db.adapter.UserAdapter
import com.pra.retrofit_db.app.MyApp
import com.pra.retrofit_db.databinding.ActivityMainBinding
import com.pra.retrofit_db.db.AppDataBase
import com.pra.retrofit_db.model.response.UserResponseModel
import com.pra.retrofit_db.model.user.User
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity(), OnItemClickListener {

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

    private var mListUser: MutableList<User> = ArrayList()

    private lateinit var db: AppDataBase;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        db = getApplicationDb()

        runBlocking {
         /*   val jobDeleteAll = launch {
                db.userDaO().deleteAllUser()
            }
            jobDeleteAll.join()
            val savedDeferred = launch {
                db.userDaO().saveUser(randomUserApiResponse?.users!!)
            }
            savedDeferred.join()*/
            val deferred = async {
                db.userDaO().getAllUser()
            }
            val resu = deferred.await()

            mListUser.clear()
            mListUser.addAll(resu)
            setAdapter()
        }

        //  getUserAPiCalling()
        //  getData()
        //    mUserAdapter = UserAdapter(mListUser, this)
        //   mBinding.rvUsers.adapter = mUserAdapter
    }

    public fun getApplicationDb(): AppDataBase {
        //if (db == null) {
            db = AppDataBase.getDatabase(this)

        return db!!
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter() {
        if (mUserAdapter == null) {
            mUserAdapter = UserAdapter(mListUser, this, this)
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


                            //  val list =MyApp.getMyApp().getApplicationDb().userDaO().getAllUser()
                            runBlocking {
                                val jobDeleteAll = launch {
                                    db.userDaO().deleteAllUser()
                                }
                                jobDeleteAll.join()
                                val savedDeferred = launch {
                                    db.userDaO().saveUser(randomUserApiResponse?.users!!)
                                }
                                savedDeferred.join()
                                val deferred = async {
                                    db.userDaO().getAllUser()
                                }
                                val resu = deferred.await()

                                mListUser.clear()
                                mListUser.addAll(resu)
                                setAdapter()
                            }
                            //   mListUser.clear()
                            //   mListUser.addAll(list)
                            // setAdapter()
                        } else {
                            Toast.makeText(
                                this@SecondActivity,
                                "" + response.errorBody().toString(),
                                Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                    response.errorBody() != null ->
                        Toast.makeText(
                            this@SecondActivity,
                            "" + response.errorBody().toString(),
                            Toast.LENGTH_SHORT
                        ).show();
                    else ->
                        Toast.makeText(
                            this@SecondActivity,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show();
                }
            }

            override fun onFailure(call: Call<UserResponseModel>, t: Throwable) {
                Toast.makeText(this@SecondActivity, "" + t.message, Toast.LENGTH_SHORT).show();
            }
        })
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this,"Click",Toast.LENGTH_SHORT).show()
    }


}