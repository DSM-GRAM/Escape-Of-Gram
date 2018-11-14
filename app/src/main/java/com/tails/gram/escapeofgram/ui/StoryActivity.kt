package com.tails.gram.escapeofgram.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tails.gram.escapeofgram.R
import com.tails.gram.escapeofgram.ui.dialog.YesNoDialog
import com.tails.gram.escapeofgram.util.Util
import kotlinx.android.synthetic.main.activity_story.*

class StoryActivity : AppCompatActivity(), View.OnClickListener{

    private var storyIndex = 0
    private val story = arrayListOf(
        """
지금은 오후 11시,
과제 제출 1시간 전,
나는 과제가 들어있는 USB를
교실에 두고 온 것을 깨달았다.
놓고 온 자신의 휴대폰을
들고와달라는 친구의 말과 함께
나는 늦은 밤 학교로 향한다.
"""
        ,
        """
교실문이 열려져 있던 덕에
나는 USB와 친구의 휴대폰을
쉽게 찾을 수 있었다.
이제 다시 집으로 가려던 순간
늦은 밤 내가 교실에 있다는걸
알 리가 없던 수위아저씨는
학교 문을 잠가버렸다.
"""
        ,
        """
지금은 과제 제출까지 40분 전,
집까지 가는데 20분,
과제를 제출하는데 10분이
걸릴것을 생각하면
10분안에 이곳을 탈출해야한다.
""")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        story_tv.setWriteDelay(125)
        story_tv.typingText(story[storyIndex])

        next_so_btn.setSubscribeTextWriter(story_tv)
        next_so_btn.setOnClickListener(this)
        previous_so_btn.setOnClickListener(this)
    }

    override fun onClick(v : View){
        if(v.visibility  == View.VISIBLE) {
            when (v.id) {
                R.id.next_so_btn -> {
                    if (storyIndex < 2) {
                        if (storyIndex == 0) previous_so_btn.setSubscribeTextWriter(story_tv)
                        story_tv.typingText(story[++storyIndex])
                    }else{
                        YesNoDialog("시작 하시겠습니까?").apply {
                            this.touchEvent = View.OnClickListener {
                                when(it.id) {
                                    R.id.no_btn -> {
                                        Util.setYesNoDismiss(this.dialog)
                                    }
                                    R.id.yes_btn -> {
                                        startActivity(Intent(applicationContext, GameActivity::class.java))
                                        finish()
                                    }
                                }
                            }
                            this.isCancelable = false
                            this.show(supportFragmentManager, "YesNo")
                        }
                    }
                }
                R.id.previous_so_btn -> {
                    if (storyIndex > 0) story_tv.typingText(story[--storyIndex])
                }
            }
            when (storyIndex) {
                0 -> previous_so_btn.observingEnabled = false
                2 -> next_so_btn.text = "시작"
                else -> {
                    next_so_btn.text = "다음"
                    previous_so_btn.observingEnabled = true
                }
            }
        }
    }

    override fun onBackPressed() {}
}
