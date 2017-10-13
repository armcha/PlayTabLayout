package io.armcha.playtablayout.common

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.Log
import android.util.Property
import android.view.View

/**
 * Created by arman.chatikyan on 10/12/2017.
 */

internal fun Context.color(colorResId: Int) = ContextCompat.getColor(this, colorResId)

internal inline fun log(body: () -> Any?) {
    Log.e("TAG", body().toString())
}

internal inline fun on21orAbove(up: () -> Unit, noinline down: () -> Unit = {}) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        up()
    } else {
        down()
    }
}