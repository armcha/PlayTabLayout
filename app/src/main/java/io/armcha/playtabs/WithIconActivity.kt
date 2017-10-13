package io.armcha.playtabs

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import io.armcha.playtablayout.core.TouchableTabLayout
import kotlinx.android.synthetic.main.activity_main.*

class WithIconActivity : AppCompatActivity(), TouchableTabLayout.OnTabSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_icon)

        playTabLayout.colors = intArrayOf(R.color.f,
                R.color.s,
                R.color.t,
                R.color.four)

        val tabLayout = playTabLayout.tabLayout
        viewPager.adapter = TabAdapter(supportFragmentManager)

        with(tabLayout) {
            setupWithViewPager(viewPager)
            setSelectedTabIndicatorHeight(7)
            setSelectedTabIndicatorColor(Color.WHITE)
            tabMode = TabLayout.MODE_FIXED
            tabGravity = TabLayout.GRAVITY_FILL
            setTabTextColors(ContextCompat.getColor(this@WithIconActivity, R.color.unselected_tab_color), Color.WHITE)
            addOnTabSelectedListener(this@WithIconActivity)
        }

        fun icon(index: Int, drawableId: Int) {
            tabLayout.getTabAt(index)?.setIcon(drawableId)
        }
        icon(0, R.drawable.audiobook)
        icon(1, R.drawable.google_play)
        icon(2, R.drawable.filmstrip)
        icon(3, R.drawable.book_open_variant)

        fun Drawable.tint(color: Int) {
            setColorFilter(ContextCompat.getColor(this@WithIconActivity, color), PorterDuff.Mode.SRC_IN)
        }
        (0 until viewPager.adapter.count)
                .map { tabLayout.getTabAt(it) }
                .map { it?.getIcon() }
                .doWhen({ it?.tint(R.color.selected_tab_color) }, { it == viewPager.currentItem })
                .doWhen({ it?.tint(R.color.unselected_tab_color) }, { it != viewPager.currentItem })
    }

    private inline fun <T> Iterable<T>.doWhen(body: (T) -> Unit, predicate: (Int) -> Boolean): Iterable<T> {
        var index = 0
        return apply {
            for (element in this) {
                if (predicate(index++)) {
                    body(element)
                }
            }
        }
    }

    override fun onTabSelected(tab: TouchableTabLayout.Tab) {
        tab.getIcon()?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
    }

    override fun onTabUnselected(tab: TouchableTabLayout.Tab) {
        tab.getIcon()?.setColorFilter(ContextCompat.getColor(this, R.color.unselected_tab_color), PorterDuff.Mode.SRC_IN);
    }

    override fun onTabReselected(tab: TouchableTabLayout.Tab) {
    }
}