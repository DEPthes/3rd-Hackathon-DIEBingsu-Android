package com.depth.diebingsu

import androidx.fragment.app.Fragment
import com.depth.diebingsu.databinding.ActivityMainBinding
import com.depth.diebingsu.presentation.base.BaseActivity
import com.depth.diebingsu.presentation.utils.KakaoShareManager
import com.depth.diebingsu.presentation.view.main.ResultFragment
import java.io.File

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView() {
        replaceFragment(ResultFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}