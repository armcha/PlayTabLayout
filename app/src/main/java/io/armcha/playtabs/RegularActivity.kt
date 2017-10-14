package io.armcha.playtabs

import android.graphics.Color
import android.os.Bundle
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

        with(playTabLayout.tabLayout) {
            setupWithViewPager(viewPager)
            setSelectedTabIndicatorHeight(7)
            setSelectedTabIndicatorColor(Color.WHITE)
            setTabTextColors(ContextCompat.getColor(this@RegularActivity, R.color.unselected_tab_color), Color.WHITE)
        }
    }
}
