package com.depth.diebingsu.presentation.view.feed

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.depth.diebingsu.R
import com.depth.diebingsu.data.dto.Information
import com.depth.diebingsu.databinding.FragmentFeedBinding
import com.depth.diebingsu.presentation.base.BaseFragment
import com.depth.diebingsu.presentation.utils.UiState


class FeedFragment : BaseFragment<FragmentFeedBinding>(R.layout.fragment_feed) {
    private val feedViewModel : FeedViewModel by viewModels()
    private lateinit var bingsuWorldAdapter: RvBingsuWorldAdapter
    override fun initView() {
        val data = mutableListOf<Information>()
        bingsuWorldAdapter = RvBingsuWorldAdapter().apply {

        }
        bingsuWorldAdapter.list = data
        binding.rvBingsuWorld.adapter = bingsuWorldAdapter
        binding.rvBingsuWorld.layoutManager = LinearLayoutManager(requireContext())
        observer()

        feedViewModel.getBoard(getAndroidId())

        Log.d("TEST", "TESTING")
    }

    override fun observer() {
        feedViewModel.boardState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Failure -> {
                    Log.d("OBS", "사진 로딩 실패")
                }
                is UiState.Loading -> {}
                is UiState.Success -> {
                    bingsuWorldAdapter.setData(it.data)
                }
            }
        }
    }

    fun getAndroidId(): String {
        return Settings.Secure.getString(requireContext().contentResolver, Settings.Secure.ANDROID_ID)
    }
}