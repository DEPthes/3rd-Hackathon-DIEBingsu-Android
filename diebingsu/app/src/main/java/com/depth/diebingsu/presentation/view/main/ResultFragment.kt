package com.depth.diebingsu.presentation.view.main

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.depth.diebingsu.R
import com.depth.diebingsu.databinding.FragmentResultBinding
import com.depth.diebingsu.presentation.base.BaseFragment
import com.depth.diebingsu.presentation.view.feed.FeedActivity
import com.depth.diebingsu.presentation.view.feed.FeedFragment

class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    override fun initView() {
        Log.d("DeviceID", "${getAndroidId(requireContext())}")
    }

    override fun initListener() {
        super.initListener()
        binding.btnDownload.setOnClickListener {
            useDownloadManager()
            Toast.makeText(requireContext(), "갤러리에 저장 되었습니다.", Toast.LENGTH_LONG).show()
        }
        binding.btnPost.setOnClickListener {
            val intent = Intent(requireContext(), FeedActivity::class.java)
            startActivity(intent)
        }
        binding.btnShare.setOnClickListener {
            ShareFragment().show(requireActivity().supportFragmentManager, "share")
        }
    }

    override fun observer() {
        super.observer()
    }

    // 사진 다운로드
    private fun useDownloadManager(){
        val currentTimeMillis = System.currentTimeMillis()
        val fileName = currentTimeMillis.toString()

        val request = DownloadManager.Request(Uri.parse(""))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, fileName)

        val downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)

        Toast.makeText(requireContext(), "다운로드 완료", Toast.LENGTH_SHORT).show()
    }
    fun getAndroidId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}