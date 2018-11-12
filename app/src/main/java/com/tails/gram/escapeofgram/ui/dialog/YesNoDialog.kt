package com.tails.gram.escapeofgram.ui.dialog

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tails.gram.escapeofgram.R
import com.tails.gram.escapeofgram.ui.GameActivity
import kotlinx.android.synthetic.main.yes_no_dialog.*

class YesNoDialog : DialogFragment(), View.OnClickListener {
    private lateinit var mDialog: Dialog

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

        no_btn.setOnClickListener(this)
        yes_btn.setOnClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.yes_no_dialog, container, false)

    override fun onClick(v: View) {
        when (v.id) {
            R.id.no_btn -> {
                dismiss()
            }
            R.id.yes_btn -> {
                startActivity(Intent(context, GameActivity::class.java))
                activity!!.finish()
            }
        }
    }
}