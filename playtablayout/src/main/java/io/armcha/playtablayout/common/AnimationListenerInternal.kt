package io.armcha.playtablayout.common

import android.animation.Animator

/**
 * Created by arman.chatikyan on 10/13/2017.
 */
private class AnimationListenerInternal(private val start: () -> Unit = {},
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
    addListener(AnimationListenerInternal(start, end, cancel))
}

internal fun Animator.onStart(start: () -> Unit) {
    addListener(AnimationListenerInternal(start = start))
}

internal fun Animator.onEnd(end: () -> Unit) {
    addListener(AnimationListenerInternal(end = end))
}

internal fun Animator.onCancel(cancel: () -> Unit) {
    addListener(AnimationListenerInternal(cancel = cancel))
}