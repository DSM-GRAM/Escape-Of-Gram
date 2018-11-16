package com.tails.gram.escapeofgram.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tails.gram.escapeofgram.R
import com.tails.gram.escapeofgram.connect.RetrofitUtil
import kotlinx.android.synthetic.main.activity_ranking.*
import kotlinx.android.synthetic.main.ranking_item.*

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        RetrofitUtil.getRank(applicationContext, rank_recycler, intent.getStringExtra("name"), rank_name_tv, rank_tv, rank_time_tv)
    }
}
