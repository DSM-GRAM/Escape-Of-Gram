package com.tails.gram.escapeofgram.connect

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil{
    val URL = "URL 좀 주세요 제발.."

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postService = retrofit!!.create(API::class.java)
}