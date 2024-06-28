package com.depth.diebingsu

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.depth.diebingsu.databinding.ActivityFeedBinding
import com.depth.diebingsu.databinding.ActivityMainBinding
import com.depth.diebingsu.presentation.base.BaseActivity
import com.depth.diebingsu.presentation.view.main.BingsuWorldFragment
import com.depth.diebingsu.presentation.view.main.MainFragment
import com.depth.diebingsu.presentation.view.main.SlotMachineFragment

class FeedActivity : BaseActivity<ActivityFeedBinding>(R.layout.activity_feed) {
    override fun initView() {
        replaceFragment(BingsuWorldFragment(), false)
        setupTabs()
    }

    override fun initListener() {
        super.initListener()

    }

    fun replaceFragment(fragment: Fragment, isAddBackStack: Boolean){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_feed, fragment)
        if(isAddBackStack) ft.addToBackStack(null)
        ft.commit()
    }

    private fun setupTabs() {
        val tabLayout = binding.tabLayout

        val tabTitles = arrayOf("Tab A", "Tab B")
        val tabIcons = arrayOf(R.drawable.ic_bingsu_world_txt, R.drawable.ic_my_bingsu_txt)

        for (i in tabTitles.indices) {
            val tab = tabLayout.newTab()
            val tabView = LayoutInflater.from(this).inflate(R.layout.tab_item, null)
            val tabIcon1 = tabView.findViewById<ImageView>(R.id.tab_bingsu_world)
            val tabIcon2 = tabView.findViewById<ImageView>(R.id.tab_my_bingsu)

            if (i == 0) {
                tabIcon1.setImageResource(tabIcons[i])
                tabIcon1.visibility = View.VISIBLE
                tabIcon2.visibility = View.GONE
            } else {
                tabIcon1.visibility = View.GONE
                tabIcon2.setImageResource(tabIcons[i])
                tabIcon2.visibility = View.VISIBLE
            }

            tab.customView = tabView
            tabLayout.addTab(tab)
        }
    }
}