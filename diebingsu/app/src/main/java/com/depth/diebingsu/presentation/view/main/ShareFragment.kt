package com.depth.diebingsu.presentation.view.main

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.depth.diebingsu.MainActivity
import com.depth.diebingsu.R
import com.depth.diebingsu.databinding.FragmentShareBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File

class ShareFragment : BottomSheetDialogFragment() {
    val binding by lazy {
        FragmentShareBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val behavior = BottomSheetBehavior.from<View>(binding.bottomsheet!!)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        binding.btnShareKakao.setOnClickListener {

        }

        binding.btnShareInsta.setOnClickListener {
            //인스타그램 설치 여부 확인
            val shareIntent = Intent().apply {
                val mediaFile = File(getURLForResource(R.drawable.ic_photo_img))
                // file extension 으로 부터 타입 추론 ex)image/jpeg
                val mediaType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(mediaFile.extension)

                // contentUri 셋팅
                // 기타 프로퍼티 설정
                putExtra(Intent.EXTRA_STREAM, mediaFile)
                type = mediaType
                action = Intent.ACTION_SEND
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                setDataAndType(null, "image/jpeg");
            }

            startActivity(Intent.createChooser(shareIntent, ""))
        }

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), theme)
        bottomSheetDialog.setOnShowListener { dialog ->
            val bottomSheet =
                (dialog as BottomSheetDialog).findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED
            BottomSheetBehavior.from(bottomSheet).skipCollapsed = true
            BottomSheetBehavior.from(bottomSheet).isHideable = true
        }
        return bottomSheetDialog
    }
    fun getURLForResource(resId: Int): String {
        return Uri.parse("android.resource://" + (R::class.java.getPackage()?.name ?: "") + "/" + resId)
            .toString()
    }
}