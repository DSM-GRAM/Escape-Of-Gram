package com.tails.gram.escapeofgram.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tails.gram.escapeofgram.R
import kotlinx.android.synthetic.main.door_lock_dialog.*
import org.jetbrains.anko.support.v4.toast

class DoorLockDialog : DialogFragment(), View.OnClickListener {
    private lateinit var mDialog: Dialog
    private var numCount = 0
    private var numArray = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "  ")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        Dialog(activity!!, R.style.DialogStyle).apply {
            window!!.attributes.windowAnimations = R.style.DialogAnimationReverse
            mDialog = this
        }

    override fun onStart() {
        super.onStart()
        mDialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickInit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.door_lock_dialog, container, false)

    private fun typeNum(n: Int) {
        when (numCount) {
            0 -> first_num.text = numArray[n]
            1 -> second_num.text = numArray[n]
            2 -> third_num.text = numArray[n]
            3 -> fourth_num.text = numArray[n]
        }
        if (numCount < 3) numCount++
    }

    private fun checkNum() {
        if (first_num.text != numArray[10] &&
            second_num.text != numArray[10] &&
            third_num.text != numArray[10] &&
            fourth_num.text != numArray[10]
        ) {
            val num = "${first_num.text}${second_num.text}${third_num.text}${fourth_num.text}"
            if(num == "1234"){
                toast("성공")
            }
        }else{
            toast("숫자 4글자를 입력하세요.")
        }
    }

    private fun deleteNum() {
        when (numCount) {
            0 -> first_num.text = "  "
            1 -> second_num.text = "  "
            2 -> third_num.text = "  "
            3 -> fourth_num.text = "  "
        }
        if (numCount > 0) numCount--
    }

    private fun setClickInit(){
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        zero.setOnClickListener(this)
        check.setOnClickListener(this)
        delete.setOnClickListener(this)
        back_btn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.one -> typeNum(0)
            R.id.two -> typeNum(1)
            R.id.three -> typeNum(2)
            R.id.four -> typeNum(3)
            R.id.five -> typeNum(4)
            R.id.six -> typeNum(5)
            R.id.seven -> typeNum(6)
            R.id.eight -> typeNum(7)
            R.id.nine -> typeNum(8)
            R.id.zero -> typeNum(9)
            R.id.check -> checkNum()
            R.id.delete -> deleteNum()
            R.id.back_btn -> dismiss()
        }
    }
}