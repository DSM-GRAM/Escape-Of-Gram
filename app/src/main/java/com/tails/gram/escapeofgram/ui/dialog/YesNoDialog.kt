package com.tails.gram.escapeofgram.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tails.gram.escapeofgram.R
import kotlinx.android.synthetic.main.yes_no_dialog.*
import kotlinx.android.synthetic.main.yes_no_dialog.view.*

@SuppressLint("ValidFragment")
class YesNoDialog(val message : String) : DialogFragment(){
    private lateinit var mDialog : Dialog
    lateinit var touchEvent : View.OnClickListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        Dialog(activity!!, R.style.DialogStyle).apply {
            window!!.attributes.windowAnimations = R.style.DialogAnimation
            mDialog = this
    }

    override fun onStart() {
        super.onStart()
        mDialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yes_btn.setOnClickListener(touchEvent)
        no_btn.setOnClickListener(touchEvent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val v = inflater.inflate(R.layout.yes_no_dialog, container, false)
        v.rootView.message_tv.text = message

        return  v
    }
}