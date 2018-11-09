package com.tails.gram.escapeofgram.ui.view

import android.content.Context
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import com.tails.gram.escapeofgram.ui.observe.IsSpokenObserver
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
            }
        }
    }

    val speakObserver = IsSpokenObserver()
    private var tts = TextToSpeech(context, this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    fun typingText(txt: CharSequence) {
        mText = txt
        mIndex = 0
        text = ""

        if(tts.isSpeaking) {
            tts.stop()
            speak(txt.toString())
        }else{
            speak(txt.toString())
        }

        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, mDelay)
    }

    fun setWriteDelay(m: Long) {
        mDelay = m
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val language = tts.setLanguage(Locale.KOREA)
            if (language == TextToSpeech.LANG_MISSING_DATA
                || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("ERROR!", "Language is not available.")
            }else{
                tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(p0: String?) {}
                    override fun onError(p0: String?) {}
                    override fun onDone(p0: String?) {
                        speakObserver.setState(true)
                    }
                })
                tts.setPitch(0.05f)
                tts.setSpeechRate(1.0f)
                speak(mText.toString())
            }
        }
    }

    private fun speak(text: String) {
        tts.speak(text.replace("\n", " "), TextToSpeech.QUEUE_FLUSH, null, "Spoken")
        speakObserver.setState(false)
    }
}