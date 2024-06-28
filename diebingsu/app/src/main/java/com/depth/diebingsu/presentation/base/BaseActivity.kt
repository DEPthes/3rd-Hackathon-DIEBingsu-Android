package com.depth.diebingsu.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.depth.diebingsu.core.util.LoggerUtils

abstract class BaseActivity<T: ViewDataBinding>(@LayoutRes private val layoutId: Int): AppCompatActivity() {
    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoggerUtils.d("onCreate: $localClassName")

        binding = DataBindingUtil.setContentView(this, layoutId)
        initView()
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        LoggerUtils.d("onDestroy: $localClassName")
    }

    protected abstract fun initView()
    protected open fun initListener() {}

}