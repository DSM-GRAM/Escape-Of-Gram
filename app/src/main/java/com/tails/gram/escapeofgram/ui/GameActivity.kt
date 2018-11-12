package com.tails.gram.escapeofgram.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tails.gram.escapeofgram.R

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    override fun onBackPressed() {}
}
