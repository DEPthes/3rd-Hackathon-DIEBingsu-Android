package com.depth.diebingsu.presentation.view.feed

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.depth.diebingsu.R
import com.depth.diebingsu.databinding.ActivityFeedBinding
import com.depth.diebingsu.databinding.ActivityMainBinding
import com.depth.diebingsu.presentation.base.BaseActivity
import com.google.android.material.tabs.TabLayout

class FeedActivity : BaseActivity<ActivityFeedBinding>(R.layout.activity_feed) {
    override fun initView() {
        setTabLayout()
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.tab_layout_container, fragment)
        fragmentTransaction.commit()
    }

    private fun setTabLayout() {
        // 초기 tab 세팅

        binding.storeFragmentTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            // tab이 선택되었을 때
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        Log.d("TEST", "TESTING")
                        replaceFragment(FeedFragment())
                    }
                    1 -> binding.tabLayoutContainer.setBackgroundResource(R.color.white)
                }
            }
            // tab이 선택되지 않았을 때
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            // tab이 다시 선택되었을 때
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}