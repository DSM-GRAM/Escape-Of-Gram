package com.tails.gram.escapeofgram.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tails.gram.escapeofgram.R
import com.tails.gram.escapeofgram.ui.dialog.DoorLockDialog
import com.tails.gram.escapeofgram.ui.dialog.YesNoDialog
import com.tails.gram.escapeofgram.util.Util
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.support.v4.longToast
import java.util.concurrent.TimeUnit

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private val timeFormat = "%02d:%02d"
    private var hintNum = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        object : CountDownTimer(600000, 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                Util.timeFormatResult = ""+String.format(timeFormat,
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                left_time_tv.text = Util.timeFormatResult
                if(Util.time % 90 == 0){
                    if(hintNum < 3) {
                        hintNum++
                        when (hintNum) {
                            0 -> first_hint.setImageResource(R.drawable.paper_plane_puple)
                            1 -> second_hint.setImageResource(R.drawable.paper_plane_puple)
                            2 -> third_hint.setImageResource(R.drawable.paper_plane_puple)
                        }
                    }
                }
                --Util.time
            }

            override fun onFinish() {
                startActivity(Intent(applicationContext, RankingActivity::class.java).putExtra("isComplete", false))
                longToast("아쉽게도 시간초과 되었습니다...ㅠ\n마지막까지 부스에 참여해 주셔서 감사합니다!\n스텝에게 가주시길 바랍니다.  :)")
                finish()
            }
        }.start()

        give_up_btn.setOnClickListener(this)
        complete_btn.setOnClickListener(this)
        first_hint.setOnClickListener(this)
        second_hint.setOnClickListener(this)
        third_hint.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.give_up_btn->{
                YesNoDialog("포기 하시겠습니까?").apply {
                    this.touchEvent = View.OnClickListener {
                        when (it.id) {
                            R.id.no_btn -> Util.setYesNoDismiss(this.dialog)
                            R.id.yes_btn -> {
                                startActivity(Intent(applicationContext, RankingActivity::class.java).putExtra("isComplete", false))
                                longToast("부스에 참여해 주셔서 감사합니다!\n 스텝에게 가주시길 바랍니다.  :)")
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
            R.id.first_hint->{
                if(hintNum > -1) {
                    first_hint.setImageResource(R.drawable.paper_plane_white)
                    Util.showHindDialog(Util.story[0], supportFragmentManager)
                }
            }
            R.id.second_hint->{
                if(hintNum > 0) {
                    second_hint.setImageResource(R.drawable.paper_plane_white)
                    Util.showHindDialog(Util.story[1], supportFragmentManager)
                }
            }
            R.id.third_hint->{
                if(hintNum > 1) {
                    third_hint.setImageResource(R.drawable.paper_plane_white)
                    Util.showHindDialog(Util.story[2], supportFragmentManager)
                }
            }
        }
    }

    override fun onBackPressed() {}
}
