package com.tails.gram.escapeofgram.connect

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.JsonObject
import com.tails.gram.escapeofgram.model.Rank
import com.tails.gram.escapeofgram.ui.RankingActivity
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil{
    private val URL = "http://13.59.116.83/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val postService: API = retrofit!!.create(API::class.java)

    fun getRank(context : Context){
        RetrofitUtil.postService.getRank().enqueue(object : Res<ArrayList<Rank>>(context){
            override fun callback(code: Int, body: ArrayList<Rank>?) {
                if(code == 200){
                    body!!.forEach {
                        Log.e("rank", "${it.name} ${it.time}")
                    }
                }else{
                    Log.e("code", code.toString())
                }
            }
        })
    }

    fun addRank(context : Context, activity: Activity, name : String, time : String){
        val body = JsonObject()

        body.addProperty("name", name)
        body.addProperty("time", time)

        RetrofitUtil.postService.addRank(body).enqueue(object : Res<Void>(context){
            override fun callback(code: Int, body: Void?) {
                if(code == 201){
                    context.startActivity(Intent(context, RankingActivity::class.java))
                    context.toast("랭크에 갱신되었습니다.")
                    activity.finish()
                }else{
                    Log.e("code", code.toString())
                }
            }
        })
    }
}