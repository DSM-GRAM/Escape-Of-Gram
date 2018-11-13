package com.tails.gram.escapeofgram.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rank(
    @SerializedName("name")
    @Expose
    val name : String,

    @SerializedName("time")
    @Expose
    val time : String)