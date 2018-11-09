package com.tails.gram.escapeofgram.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tails.gram.escapeofgram.R
import com.tails.gram.escapeofgram.ui.observe.IsSpokenSubscriber
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tmp  = "지금은 오후 11시,\n" +
                "과제 제출 1시간 전,\n" +
                "나는 과제가 들어있는 USB를\n" +
                "교실에 두고 온 것을 깨달았다.\n" +
                "놓고 온 자신의 휴대폰을\n" +
                "들고와달라는 친구의 말과 함께\n" +
                "나는 늦은 밤 학교로 향한다."
        test.setWriteDelay(125)
        test.typingText(tmp)

        val isSpokenSubcriber = IsSpokenSubscriber(test.speakObserver)
    }
}
