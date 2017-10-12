package io.armcha.playtablayout.core

import android.animation.Animator
import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import io.armcha.playtablayout.R
import io.armcha.playtablayout.common.color
import io.armcha.playtablayout.common.params


/**
 * Created by arman.chatikyan on 10/12/2017.
 */

class PlayTabLayout : FrameLayout, TouchableTabLayout.TabClickListener {

    private var animator: Animator? = null
    val tabLayout: TouchableTabLayout
    val tabColorHolder: FrameLayout
    var colors = intArrayOf()
        set(value) {
            setBackgroundColor(context.color(value[0]))
            field = value
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        ViewCompat.setElevation(this, 20F)
        ViewCompat.setTranslationZ(this, 20F)
        tabLayout = TouchableTabLayout(context)
        tabColorHolder = FrameLayout(context)
        addView(tabColorHolder, params.MATCH_PARENT, params.MATCH_PARENT)
        addView(tabLayout, params.MATCH_PARENT, params.MATCH_PARENT)
        tabLayout.tabClickListener = this
    }

    override fun onTabClicked(selected: Int, fromTouch: Boolean, event: MotionEvent?) {
        animator?.cancel()
        val startRadius = 0F
        val endRadius = Math.hypot(tabLayout.width.toDouble(), tabLayout.height.toDouble()).toFloat()
        animator = if (fromTouch && event != null) {
            ViewAnimationUtils.createCircularReveal(tabColorHolder, event.rawX.toInt(), event.y.toInt(), startRadius, endRadius)
        } else {
            if (tabColorHolder.isAttachedToWindow) {
                tabLayout.mViewPager?.let {
                    val oneTabWidth = tabLayout.width / it.adapter.count
                    val centerX = (oneTabWidth / 2) + oneTabWidth * selected
                    val centerY = tabColorHolder.height - context.resources.getDimension(R.dimen.tab_bottom_dimen_ripple).toInt()
                    ViewAnimationUtils.createCircularReveal(tabColorHolder, centerX, centerY, startRadius, endRadius)
                }
            } else {
                setBackgroundColor(context.color(colors[selected]))
                null
            }
        }

        animator?.duration = 550
        animator?.interpolator = FastOutSlowInInterpolator()
        animator?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                setBackgroundColor(context.color(colors[selected]))
            }

            override fun onAnimationCancel(p0: Animator?) {
                setBackgroundColor(context.color(colors[selected]))
            }

            override fun onAnimationStart(p0: Animator?) {
                tabColorHolder.setBackgroundColor(context.color(colors[selected]))
            }
        })
        animator?.start()
    }
}