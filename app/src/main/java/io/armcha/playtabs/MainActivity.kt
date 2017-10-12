package io.armcha.playtabs

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import io.armcha.playtablayout.common.color
import io.armcha.playtablayout.core.TouchableTabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), TouchableTabLayout.OnTabSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playTabLayout.colors = intArrayOf(R.color.f, R.color.s, R.color.t, R.color.four)

        val tabLayout = playTabLayout.tabLayout
        viewPager.adapter = TabAdapter(supportFragmentManager)

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setSelectedTabIndicatorHeight(7)
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE)
        tabLayout.tabMode = TabLayout.MODE_FIXED
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.setTabTextColors(color(R.color.unselected_tab_color), Color.WHITE)
        tabLayout.addOnTabSelectedListener(this)

        fun icon(index: Int, drawableId: Int) {
            tabLayout.getTabAt(index)?.setIcon(drawableId)
        }
        icon(0, R.drawable.audiobook)
        icon(1, R.drawable.google_play)
        icon(2, R.drawable.filmstrip)
        icon(3, R.drawable.book_open_variant)

        (0 until viewPager.adapter.count)
                .map { tabLayout.getTabAt(it) }
                .map { it?.getIcon() }
                .forEachIndexed { index, drawable ->
                    if (index == viewPager.currentItem)
                        drawable?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
                    else
                        drawable?.setColorFilter(color(R.color.unselected_tab_color), PorterDuff.Mode.SRC_IN)
                }
    }

    override fun onTabSelected(tab: TouchableTabLayout.Tab) {
        tab.getIcon()?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
    }

    override fun onTabUnselected(tab: TouchableTabLayout.Tab) {
        tab.getIcon()?.setColorFilter(color(R.color.unselected_tab_color), PorterDuff.Mode.SRC_IN);
    }

    override fun onTabReselected(tab: TouchableTabLayout.Tab) {
    }
}
