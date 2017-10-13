package io.armcha.playtablayout.core

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v4.view.ViewCompat
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import io.armcha.playtablayout.R
import io.armcha.playtablayout.common.*
import kotlin.properties.Delegates


/**
 * Created by arman.chatikyan on 10/12/2017.
 */

class PlayTabLayout : FrameLayout, TouchableTabLayout.TabClickListener {

    private val ANIMATION_DURATION = 550L

    private var animator: Animator? = null
    private var tabColorHolder: FrameLayout by Delegates.notNull()
    var tabLayout: TouchableTabLayout  by Delegates.notNull()

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
        on21orAbove(up = {
            animate(fromTouch, event, selected)
        }, down = {
            ObjectAnimator.ofInt(tabColorHolder,
                    tabColorHolder.BACKGROUND_COLOR, color(colors[selected]))
                    .apply {
                        duration = ANIMATION_DURATION
                        setEvaluator(ArgbEvaluator())
                        interpolator = FastOutSlowInInterpolator()
                        start()
                    }
        })
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animate(fromTouch: Boolean, event: MotionEvent?, selected: Int) {
        val startRadius = 0F
        val endRadius = Math.hypot(tabLayout.width.toDouble(), tabLayout.height.toDouble()).toFloat()
        val color = color(colors[selected])
        animator?.cancel()
        animator = if (fromTouch && event != null) {
            ViewAnimationUtils.createCircularReveal(tabColorHolder, event.rawX.toInt(), event.y.toInt(), startRadius, endRadius)
        } else {
            if (ViewCompat.isAttachedToWindow(tabColorHolder)) {
                tabLayout.mViewPager?.let {
                    fun dimen(dimenResId: Int) = context.resources.getDimension(dimenResId).toInt()
                    val oneTabWidth = tabLayout.mSelectedTab?.mView?.width ?: 1
                    val centerX = (oneTabWidth / 2) + oneTabWidth * selected
                    val hasIcon = tabLayout.getTabAt(selected)?.getIcon() != null
                    val paddingBottom = if (hasIcon)
                        dimen(R.dimen.tab_bottom_dimen_ripple_with_icon)
                    else
                        dimen(R.dimen.tab_bottom_dimen_ripple)
                    val centerY = tabColorHolder.height - paddingBottom
                    ViewAnimationUtils.createCircularReveal(tabColorHolder, centerX, centerY, startRadius, endRadius)
                }
            } else {
                setBackgroundColor(color)
                null
            }
        }
        animator?.run {
            duration = ANIMATION_DURATION
            interpolator = FastOutSlowInInterpolator()

            listen(start = {
                tabColorHolder.setBackgroundColor(color)
            }, end = {
                setBackgroundColor(color)
            }, cancel = {
                setBackgroundColor(color)
            })
            start()
        }
    }

    private fun color(colorResId: Int) = context.color(colorResId)
}