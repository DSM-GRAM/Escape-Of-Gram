package com.tails.gram.escapeofgram.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tails.gram.escapeofgram.R
import com.tails.gram.escapeofgram.ui.dialog.YesNoDialog
import com.tails.gram.escapeofgram.util.Util
import kotlinx.android.synthetic.main.activity_story.*
import org.jetbrains.anko.longToast

class StoryActivity : AppCompatActivity(), View.OnClickListener{
    private var storyIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        story_tv.setWriteDelay(125)
        story_tv.typingText(Util.story[storyIndex])

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
                        story_tv.typingText(Util.story[++storyIndex])
                    }else{
                        YesNoDialog("시작 하시겠습니까?").apply {
                            this.touchEvent = View.OnClickListener {
                                when(it.id) {
                                    R.id.no_btn -> Util.setYesNoDismiss(this.dialog)
                                    R.id.yes_btn -> {
                                        longToast("도어락 비밀번호을 알아내서 탈출하세요!")
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
                R.id.previous_so_btn -> if (storyIndex > 0) story_tv.typingText(Util.story[--storyIndex])
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
