package com.depth.diebingsu.presentation.view.main

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.depth.diebingsu.MainActivity
import com.depth.diebingsu.R
import com.depth.diebingsu.data.remote.model.Information
import com.depth.diebingsu.databinding.FragmentLoadingBinding
import com.depth.diebingsu.databinding.FragmentSlotmachineBinding
import com.depth.diebingsu.presentation.base.BaseFragment
import com.depth.diebingsu.presentation.utils.UiState

class LoadingFragment(private val information: Information): BaseFragment<FragmentLoadingBinding>(R.layout.fragment_loading) {
    private val loadingViewModel: LoadingViewModel by viewModels()
    override fun initView() {
        startLottieAnimation()
    }

    override fun initListener() {
        super.initListener()
        loadingViewModel.getGenerate(information)
    }

    override fun observer() {
        loadingViewModel.generate.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Failure -> {
                    Log.d("TAG", "Generate fail")
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is UiState.Loading -> {
                    Log.d("TAG", "Generate loading")
                }
                is UiState.Success -> {
                    Log.d("TAG", "Generate success")
                    stopLottieAnimation()
                    //(requireActivity() as MainActivity).replaceFragment(???Fragment(), true)
                }
            }
        }
    }

    private fun startLottieAnimation() {
        binding.lottieBingsu.apply {
            playAnimation()
        }
    }

    private fun stopLottieAnimation() {
        binding.lottieBingsu.apply {
            cancelAnimation()
        }
    }
}