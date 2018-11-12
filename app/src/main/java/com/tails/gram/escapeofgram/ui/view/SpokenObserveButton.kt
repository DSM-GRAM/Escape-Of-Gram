package com.tails.gram.escapeofgram.ui.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.tails.gram.escapeofgram.ui.observe.IsSpokenObserver
import org.jetbrains.anko.runOnUiThread
import java.util.*

class SpokenObserveButton : AppCompatButton, Observer {
    var observingEnabled = true

    constructor(context: Context) : super(context)

    constructor(context: Context, atrs: AttributeSet) : super(context, atrs)


    init {
        visibility = View.INVISIBLE
    }

    fun setSubscribeTextWriter(stw: TextWriterView) {
        stw.speakObserver.addObserver(this)
    }

    override fun update(obs: Observable?, arg: Any?) {
        if (obs is IsSpokenObserver) {
            if(observingEnabled) {
                Thread(Runnable {
                    context.runOnUiThread {
                        visibility = if (obs.getState()) {
                            Log.e("SpokenTrue", obs.getState().toString())
                            View.VISIBLE
                        } else {
                            Log.e("SpokenFalse", obs.getState().toString())
                            View.INVISIBLE
                        }
                    }
                }).start()
            }
        }
    }
}