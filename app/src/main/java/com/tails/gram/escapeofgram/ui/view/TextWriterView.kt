package com.tails.gram.escapeofgram.ui.view

import android.content.Context
import android.os.Build
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class TextWriterView : TextView, TextToSpeech.OnInitListener {
    private lateinit var mText: CharSequence
    private var mIndex: Int = -1
    private var mDelay: Long = 150

    private val mHandler = Handler()
    private val characterAdder = object : Runnable {
        override fun run() {
            text = mText.subSequence(0, mIndex++)
            if (mIndex <= mText.length) {
                mHandler.postDelayed(this, mDelay)
            }else{

            }
        }
    }

    private lateinit var tts : TextToSpeech

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    fun typingText(txt: CharSequence) {
        mText = txt
        mIndex = 0
        text = ""

        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, mDelay)
        tts = TextToSpeech(context, this)
    }

    fun setWriteDelay(m: Long) {
        mDelay = m
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val language = this.tts.setLanguage(Locale.KOREA)
            if (language == TextToSpeech.LANG_MISSING_DATA
                || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("ERROR!", "Language is not available.")
            }else{
                speak(mText.toString())
            }
        }
    }

    private fun speak(text: String) {
        tts.setPitch(0.05f)
        tts.setSpeechRate(1.0f)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            tts.speak(text.replace("\n", " "), TextToSpeech.QUEUE_FLUSH, null, null)
        // API 20
        else
            tts.speak(text.replace("\n", " "), TextToSpeech.QUEUE_FLUSH, null)
    }

    class WrittenTextButton : Button{
        constructor(context: Context) : super(context)

        constructor(context: Context, attr: AttributeSet) : super(context, attr)

        var isWritten = false
            set(value) {
                visibility = if(value) View.VISIBLE else View.GONE
                field = value
            }
    }
}