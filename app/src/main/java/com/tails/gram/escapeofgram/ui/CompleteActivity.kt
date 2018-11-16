package com.tails.gram.escapeofgram.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tails.gram.escapeofgram.R
import com.tails.gram.escapeofgram.connect.RetrofitUtil
import com.tails.gram.escapeofgram.util.Util
import kotlinx.android.synthetic.main.activity_complete.*

class CompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

        complete_time_tv.text = Util.timeFormatResult
        rank_btn.setOnClickListener {
            if(!rank_name_edit.text.isEmpty()){
                RetrofitUtil.addRank(applicationContext, this, rank_name_edit.text.toString(), Util.timeFormatResult!!)
            }
        }
    }

    override fun onBackPressed() {}
}
