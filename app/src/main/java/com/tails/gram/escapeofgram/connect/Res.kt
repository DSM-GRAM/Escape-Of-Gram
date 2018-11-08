package com.tails.gram.escapeofgram.connect

import android.content.Context
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class Res<T>(val context : Context) : Callback<T>{

    override fun onFailure(call: Call<T>, t: Throwable) {
        context.toast("오류 났어요.. ㅠㅠ")
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        callback(response.code(), response.body())
    }

    abstract fun callback(code : Int, body : T?)
}