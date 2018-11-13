package com.tails.gram.escapeofgram.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.tails.gram.escapeofgram.R
import kotlinx.android.synthetic.main.activity_game.*
import java.util.concurrent.TimeUnit

class GameActivity : AppCompatActivity() {
    private var time = 600
    private val timeFormat = "%02d:%02d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        object : CountDownTimer(600000, 1000){
            override fun onFinish() {}

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                left_time_tv.text = ""+String.format(timeFormat,
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                --time
            }
        }.start()
    }
    //RetrofitUtil.getRank(applicationContext)
    //RetrofitUtil.addRank(applicationContext, "Son Seungyong", "09:59")

    override fun onBackPressed() {}
}
