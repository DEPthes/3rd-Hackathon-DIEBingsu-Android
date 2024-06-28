package com.depth.diebingsu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.depth.diebingsu.databinding.ActivityMainBinding
import com.depth.diebingsu.presentation.base.BaseActivity
import com.depth.diebingsu.presentation.utils.KakaoShareManager

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView() {

    }

    override fun initListener() {
        super.initListener()

        binding.btnShare.setOnClickListener{
            KakaoShareManager(this).doShare("invitation")
        }
    }
}