package com.theta.masterinaspnetcore.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClient {
    fun getDevApi(): DevApi {
        val mGson = GsonBuilder ().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://web.thetateam.com/theta-wp/wp-json/wp/v2/")
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .build()
        return  retrofit.create(DevApi::class.java)
    }
}