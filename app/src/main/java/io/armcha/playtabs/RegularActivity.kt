package io.armcha.playtabs

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class RegularActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playTabLayout.colors = intArrayOf(R.color.f,
                R.color.s,
                R.color.t,
                R.color.four)

        viewPager.adapter = TabAdapter(supportFragmentManager)

        val tabLayout = playTabLayout.tabLayout

        with(tabLayout) {
            setupWithViewPager(viewPager)
            setSelectedTabIndicatorHeight(7)
            tabMode = TabLayout.MODE_FIXED
            tabGravity = TabLayout.GRAVITY_FILL
            setSelectedTabIndicatorColor(Color.WHITE)
            setTabTextColors(ContextCompat.getColor(this@RegularActivity, R.color.unselected_tab_color), Color.WHITE)
        }
    }
}
