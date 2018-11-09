package com.tails.gram.escapeofgram.ui.observe

import java.util.*

class IsSpokenObserver : Observable() {
    private var isSpoken = false

    private fun stateChanged(){
        setChanged()
        notifyObservers()
    }

    fun setState(isSpoken : Boolean){
        this.isSpoken = isSpoken
        stateChanged()
    }

    fun getState() : Boolean{
        return isSpoken
    }
}