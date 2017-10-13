package io.armcha.playtablayout.common

import android.animation.Animator

/**
 * Created by arman.chatikyan on 10/13/2017.
 */
private class Testing(private val start: () -> Unit = {},
              private val end: () -> Unit = {},
              private val cancel: () -> Unit = {}) : Animator.AnimatorListener {

    override fun onAnimationEnd(animation: Animator?) {
        end()
    }

    override fun onAnimationCancel(animation: Animator?) {
        cancel()
    }

    override fun onAnimationStart(animation: Animator?) {
        start()
    }

    override fun onAnimationRepeat(animation: Animator?) {

    }
}

internal fun Animator.listen(start: () -> Unit = {},
                    end: () -> Unit = {},
                    cancel: () -> Unit = {}) {
    addListener(Testing(start, end, cancel))
}