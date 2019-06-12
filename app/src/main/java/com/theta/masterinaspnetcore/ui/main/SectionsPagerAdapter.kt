package com.theta.masterinaspnetcore.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.theta.masterinaspnetcore.R
import com.theta.masterinaspnetcore.ui.main.home.HomeFragment
import com.theta.masterinaspnetcore.ui.main.study.StudyFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_std,
    R.string.tab_home
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> StudyFragment.newInstance()
            1 -> HomeFragment.newInstance()
            else -> StudyFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}