package com.depth.diebingsu.presentation.view.main

import com.depth.diebingsu.MainActivity
import com.depth.diebingsu.R
import com.depth.diebingsu.databinding.FragmentMainBinding
import com.depth.diebingsu.presentation.base.BaseFragment

class MainFragment: BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override fun initView() {
    }

    override fun initListener() {
        super.initListener()
        binding.ibMakeBingsu.setOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(SlotMachineFragment(), true)
        }
    }
}