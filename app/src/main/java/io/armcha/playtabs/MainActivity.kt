package io.armcha.playtabs

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import io.armcha.playtablayout.common.color
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playTabLayout.colors = intArrayOf(R.color.f, R.color.s, R.color.t, R.color.four)
        val tabLayout = playTabLayout.tabLayout
        viewPager.adapter = TabAdapter(supportFragmentManager)
        tabLayout.run {
            setupWithViewPager(viewPager)
            setSelectedTabIndicatorHeight(7)
            setSelectedTabIndicatorColor(Color.WHITE)
            tabMode = TabLayout.MODE_FIXED
            tabGravity = TabLayout.GRAVITY_FILL
            setTabTextColors(context.color(R.color.unselected_tab_color), Color.WHITE)
        }
    }
}
