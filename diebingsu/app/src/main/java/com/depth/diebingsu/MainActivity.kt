package com.depth.diebingsu

import androidx.fragment.app.Fragment
import com.depth.diebingsu.databinding.ActivityMainBinding
import com.depth.diebingsu.presentation.base.BaseActivity
import com.depth.diebingsu.presentation.view.main.MainFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {
        replaceFragment(MainFragment(), true)
    }

    override fun initListener() {
        super.initListener()
    }

    fun replaceFragment(fragment: Fragment, isAddBackStack: Boolean){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_main, fragment)
        if(isAddBackStack) ft.addToBackStack(null)
        ft.commit()
    }
}