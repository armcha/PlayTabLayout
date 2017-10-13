package io.armcha.playtablayout.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Property
import android.view.View

/**
 * Created by arman.chatikyan on 10/13/2017.
 */
internal object ViewUtils {

    abstract class IntProperty<T>(name: String) : Property<T, Int>(Int::class.java, name) {

        abstract fun setValue(type: T, value: Int)

        override fun set(type: T, value: Int?) = setValue(type, value!!.toInt())
    }
}

internal val View.BACKGROUND_COLOR: Property<View, Int>
    get() = object : ViewUtils.IntProperty<View>("backgroundColor") {

        override fun setValue(type: View, value: Int) = type.setBackgroundColor(value)

        override fun get(view: View) = (background as? ColorDrawable)?.color
                ?: Color.TRANSPARENT
    }