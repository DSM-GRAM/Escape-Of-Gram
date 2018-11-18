package com.tails.gram.escapeofgram.connect

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.tails.gram.escapeofgram.model.Rank
import com.tails.gram.escapeofgram.ui.RankingActivity
import com.tails.gram.escapeofgram.ui.adapter.RankAdapter
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil{
    private const val URL = "http://13.59.116.83/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val postService: API = retrofit!!.create(API::class.java)

    fun getRank(context : Context, recycler : RecyclerView, uName : String ,name : TextView, rank : TextView, time : TextView){
        RetrofitUtil.postService.getRank().enqueue(object : Res<ArrayList<Rank>>(context){
            @SuppressLint("SetTextI18n")
            override fun callback(code: Int, body: ArrayList<Rank>?) {
                if(code == 200){
                    body!!.reverse()
                    recycler.let {
                        it.layoutManager = LinearLayoutManager(context)
                        it.adapter = RankAdapter(body).apply {
                            notifyDataSetChanged()
                        }
                    }

                    if(uName != "") {
                        var i = 0
                        while (i < body.size) {
                            if (body[i].name == uName) {
                                rank.text = "${i + 1}"
                                name.text = body[i].name
                                time.text = body[i].time.subSequence(3, body[i].time.length)
                            }
                            i++
                        }
                    }else{
                        rank.text = ""
                        name.text = ""
                        time.text = ""
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
                    val intent = Intent(context, RankingActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("name", name)
                    context.toast("랭크에 갱신되었습니다.")
                    context.startActivity(intent)
                    activity.finish()
                }else{
                    Log.e("code", code.toString())
                }
            }
        })
    }
}