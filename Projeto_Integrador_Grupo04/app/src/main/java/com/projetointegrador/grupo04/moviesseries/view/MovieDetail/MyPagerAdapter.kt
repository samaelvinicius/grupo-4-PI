package com.projetointegrador.grupo04.moviesseries.view.MovieDetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.projetointegrador.grupo04.explore.view.ExploreFragment
import com.projetointegrador.grupo04.network.NetworkFragment

/**
 * Created by Avin on 22/09/2018.
 */
class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ExploreFragment()
            }
            else -> {
                return NetworkFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Voice Call"
            else -> {
                return "Video Call"
            }
        }
    }
}