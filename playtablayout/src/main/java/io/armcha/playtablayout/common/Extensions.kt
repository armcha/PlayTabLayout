package io.armcha.playtablayout.common

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.Log

/**
 * Created by arman.chatikyan on 10/12/2017.
 */

fun Context.color(colorResId: Int) = ContextCompat.getColor(this, colorResId)

inline fun Any.log(body: () -> Any?) {
    Log.e("TAG", body().toString())
}