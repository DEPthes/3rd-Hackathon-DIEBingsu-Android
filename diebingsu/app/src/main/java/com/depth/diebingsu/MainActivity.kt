package com.depth.diebingsu

import android.content.Intent
import androidx.fragment.app.Fragment
import com.depth.diebingsu.databinding.ActivityMainBinding
import com.depth.diebingsu.presentation.base.BaseActivity
import com.depth.diebingsu.presentation.view.main.MainFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {
        replaceFragment(MainFragment(), false)
        //val intent = Intent(this, FeedActivity::class.java)
        //startActivity(intent)
    }

    fun replaceFragment(fragment: Fragment, isAddBackStack: Boolean){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_main, fragment)
        if(isAddBackStack) ft.addToBackStack(null)
        ft.commit()
    }
}