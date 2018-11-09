package com.tails.gram.escapeofgram.ui.observe

import java.util.*

class IsSpokenSubscriber(observable: IsSpokenObserver) : Observer{
    private var observable : Observable = observable
    private var isSpoken = false

    init {
        this.observable.addObserver(this)
    }

    override fun update(obs: Observable?, arg: Any?) {
        if(obs is IsSpokenObserver) {
            this.isSpoken = obs.getState()
        }
    }
}