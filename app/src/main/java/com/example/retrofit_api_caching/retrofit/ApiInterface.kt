package com.example.retrofit_api_caching.retrofit

import com.example.retrofit_api_caching.model.ModelClass
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {


@GET("v2/posts.json")
    fun getData(): Call<ModelClass>
}