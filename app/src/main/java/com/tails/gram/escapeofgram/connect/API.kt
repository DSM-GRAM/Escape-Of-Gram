package com.tails.gram.escapeofgram.connect

import com.google.gson.JsonObject
import com.tails.gram.escapeofgram.model.Rank
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API{
    @GET("rank")
    fun getRank() : Call<ArrayList<Rank>>

    @POST("rank")
    fun addRank(@Body body : JsonObject) : Call<Void>
}