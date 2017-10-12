package io.armcha.playtablayout

import android.animation.Animator
import android.content.Context
import android.graphics.Color
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewAnimationUtils
import android.widget.FrameLayout

/**
 * Created by arman.chatikyan on 10/12/2017.
 */
typealias params = FrameLayout.LayoutParams
class PlayTabLayout : FrameLayout, TouchableTabLayout.TabClickListener {

    private var animator: Animator? = null
    val tabLayout: TouchableTabLayout
    val tabColorHolder: FrameLayout
    val colors = intArrayOf(Color.GREEN, Color.RED, Color.BLUE, Color.MAGENTA)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        ViewCompat.setElevation(this, 20F)
        tabLayout = TouchableTabLayout(context)
        tabColorHolder = FrameLayout(context)
        addView(tabColorHolder, params.MATCH_PARENT, params.MATCH_PARENT)
        addView(tabLayout, params.MATCH_PARENT, params.MATCH_PARENT)
        setBackgroundColor(colors[0])
        tabLayout.tabClickListener = this
        tabLayout.mTabPaddingTop = 400//TODO
        tabLayout.invalidate()
    }

    override fun onTabClicked(selected: Int, fromTouch: Boolean, event: MotionEvent?) {
        Log.e("From", "fromTouch $fromTouch")
        animator?.cancel()

        val startRadius = 0F
        val endRadius = Math.hypot(tabLayout.width.toDouble(), tabLayout.height.toDouble()).toFloat()
        animator = if (fromTouch && event != null) {
            ViewAnimationUtils.createCircularReveal(tabColorHolder, event.rawX.toInt(), event.y.toInt(), startRadius, endRadius)
        } else {
            tabLayout.mViewPager?.let {
                val oneTabWidth = tabLayout.width / it.adapter.count
                val centerX = (oneTabWidth / 2) + oneTabWidth * selected
                val centerY = tabColorHolder.height - (tabColorHolder.height * 15 / 100)
                ViewAnimationUtils.createCircularReveal(tabColorHolder, centerX, centerY, startRadius, endRadius)
            }
        }

        animator?.duration = 650
        animator?.interpolator = FastOutSlowInInterpolator()
        animator?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                setBackgroundColor(colors[selected])
            }

            override fun onAnimationCancel(p0: Animator?) {
                setBackgroundColor(colors[selected])
            }

            override fun onAnimationStart(p0: Animator?) {
                tabColorHolder.setBackgroundColor(colors[selected])
            }
        })
        animator?.start()
    }
}