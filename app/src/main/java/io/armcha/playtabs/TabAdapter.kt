package io.armcha.playtabs

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by arman.chatikyan on 10/4/2017.
 */
class TabAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return SampleFragment()
    }

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Music"
            1 -> "Market"
            2 -> "Films"
            else -> "Books"
        }
    }
}