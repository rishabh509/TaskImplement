package com.example.taskimplement

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("posts")
    fun getAllData(): Call<ArrayList<DataModel>?>?
}