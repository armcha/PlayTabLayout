package io.armcha.playtablayout.common

import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.Log

/**
 * Created by arman.chatikyan on 10/12/2017.
 */

fun Context.color(colorResId: Int) = ContextCompat.getColor(this, colorResId)

inline fun log(body: () -> Any?) {
    Log.e("TAG", body().toString())
}

inline fun on21orAbove(body: () -> Unit, noinline down: () -> Unit = {}) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        body()
    } else {
        down()
    }
}

inline fun on19orAbove(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        body()
    }
}