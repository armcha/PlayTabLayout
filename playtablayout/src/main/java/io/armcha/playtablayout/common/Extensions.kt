package io.armcha.playtablayout.common

import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat

/**
 * Created by arman.chatikyan on 10/12/2017.
 */

internal fun Context.color(colorResId: Int) = ContextCompat.getColor(this, colorResId)

internal inline fun on21orAbove(up: () -> Unit, down: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        up()
    } else {
        down()
    }
}