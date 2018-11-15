package com.tails.gram.escapeofgram.ui.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tails.gram.escapeofgram.R
import kotlinx.android.synthetic.main.yes_no_dialog.view.*

@SuppressLint("ValidFragment")
class HintDialog(val message : String) : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val v = inflater.inflate(R.layout.hint_dialog, container, false)
        v.rootView.message_tv.text = message
        return  v
    }
}