package com.tails.gram.escapeofgram.util

import android.app.Dialog
import androidx.fragment.app.FragmentManager
import com.tails.gram.escapeofgram.ui.dialog.HintDialog

object Util{
    var life = 3
    var time = 600
    var timeFormatResult : String? = null
    val story = arrayOf(
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
    val hint = arrayOf(
        "책상 밑이 어둡니다.",  "나는 항상 비상연락망을 갖고있어요.", "요즘 커플이 참 많네요.")

    fun setYesNoDismiss(dialog : Dialog){
        dialog.dismiss()
    }

    fun showHindDialog(message : String, fragmentManager: FragmentManager){
        HintDialog(message).show(fragmentManager, "Hint")
    }
}