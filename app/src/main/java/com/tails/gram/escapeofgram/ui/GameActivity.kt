package com.tails.gram.escapeofgram.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tails.gram.escapeofgram.R
import com.tails.gram.escapeofgram.ui.dialog.DoorLockDialog
import com.tails.gram.escapeofgram.ui.dialog.YesNoDialog
import com.tails.gram.escapeofgram.util.Util
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.support.v4.toast
import java.util.concurrent.TimeUnit

class GameActivity : AppCompatActivity(), View.OnClickListener {
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

        give_up_btn.setOnClickListener(this)
        complete_btn.setOnClickListener(this)
    }

    //RetrofitUtil.getRank(applicationContext)
    //RetrofitUtil.addRank(applicationContext, "Son Seungyong", "09:59")
    override fun onClick(v: View) {
        when(v.id){
            R.id.give_up_btn->{
                YesNoDialog("포기 하시겠습니까?").apply {
                    this.touchEvent = View.OnClickListener {
                        when (it.id) {
                            R.id.no_btn -> {
                                Util.setYesNoDismiss(this.dialog)
                            }
                            R.id.yes_btn -> {
                                //startActivity(Intent(applicationContext, GameActivity::class.java))
                                finish()
                            }
                        }
                    }
                    this.isCancelable = false
                    this.show(supportFragmentManager, "YesNo")
                }
            }
            R.id.complete_btn->{
                DoorLockDialog().apply {
                    this.isCancelable = false
                    this.show(supportFragmentManager, "DoorLock")
                }
            }
        }
    }

    override fun onBackPressed() {}
}
