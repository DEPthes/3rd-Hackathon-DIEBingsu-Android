package com.depth.diebingsu.presentation.view.main

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.animation.doOnEnd
import com.depth.diebingsu.MainActivity
import com.depth.diebingsu.R
import com.depth.diebingsu.databinding.FragmentSlotmachineBinding
import com.depth.diebingsu.presentation.base.BaseFragment
import kotlin.random.Random

class SlotMachineFragment: BaseFragment<FragmentSlotmachineBinding>(R.layout.fragment_slotmachine) {
    private val ingredients = listOf(
        "딸기", "망고", "바나나", "키위", "블루베리", "수박", "복숭아", "멜론",
        "파인애플", "라즈베리", "포도", "감", "석류", "체리", "자몽", "레몬",
        "오렌지", "구아바", "용과", "패션 후르츠", "스타 후르츠", "아보카도", "라임",
        "무화과", "석류", "리치", "두리안", "망고스틴", "살구", "블랙베리", "연유",
        "초코 시럽", "캐러멜 시럽", "딸기 시럽", "망고 시럽", "꿀", "팥", "떡",
        "젤리", "아이스크림", "휘핑크림", "쿠키", "시리얼", "아몬드", "호두", "피칸",
        "초콜릿 칩", "코코넛", "젤리빈", "치즈", "피스타치오", "마카롱", "크랜베리",
        "건포도", "타피오카 펄", "마시멜로우", "말린 바나나")

    override fun initView() {
    }

    override fun initListener() {
        super.initListener()
        binding.ibHome.setOnClickListener { // 홈 화면으로 복귀
            (requireActivity() as MainActivity).replaceFragment(MainFragment(), true)
        }

        binding.ibStick.setOnClickListener { // 스틱 클릭 시 재료 랜덤 설정
            animateStick()
            animateSlotMachine()
        }

        val directTextButtons = listOf( // 직접 입력 버튼 클릭 시 포커싱
            binding.ibDirectText1 to binding.ivSlot1,
            binding.ibDirectText2 to binding.ivSlot2,
            binding.ibDirectText3 to binding.ivSlot3
        )
        for ((button, editText) in directTextButtons) {
            button.setOnClickListener {
                focusAndShowKeyboard(editText)
            }
        }

        binding.root.setOnTouchListener { v, event -> // 배경 클릭 시 키보드 숨기기
            if (event.action == MotionEvent.ACTION_DOWN) {
                hideKeyboardIfOutsideEditText(event, directTextButtons.map { it.second })
            }
            false
        }
    }

    // 슬롯 머신 스틱 클릭 애니메이션
    private fun animateStick() {
        // Set pivot to the bottom center of the view
        binding.ibStick.pivotX = (binding.ibStick.width / 2).toFloat()
        binding.ibStick.pivotY = binding.ibStick.height.toFloat()

        val rotateAnimator = ObjectAnimator.ofFloat(binding.ibStick, "rotation", 0f, 45f)
        rotateAnimator.duration = 500 // 0.5 seconds to rotate to 45 degrees
        rotateAnimator.repeatCount = 1
        rotateAnimator.repeatMode = ValueAnimator.REVERSE // Reverse back to original position
        rotateAnimator.start()
    }

    // 슬롯 머신 애니메이션 함수
    private fun animateSlotMachine() {
        val slotDuration = 1000L // Duration for each slot animation
        val slots = listOf(binding.ivSlot1, binding.ivSlot2, binding.ivSlot3)

        slots.forEachIndexed { index, editText ->
            val animator = ValueAnimator.ofInt(0, ingredients.size - 1)
            animator.duration = slotDuration
            animator.addUpdateListener { animation ->
                val value = animation.animatedValue as Int
                editText.setText(ingredients[value])
            }
            animator.startDelay = index * slotDuration / 3 // Staggered start
            animator.doOnEnd {
                if (index == slots.lastIndex) {
                    setRandomValuesToSlots(slots)
                }
            }
            animator.start()
        }
    }

    // 재료 랜덤으로 추출하는 함수
    private fun setRandomValuesToSlots(slots: List<EditText>) {
        slots.forEach { editText ->
            val randomValue = ingredients[Random.nextInt(ingredients.size)]
            editText.setText(randomValue)
        }
    }

    // 직접 입력 버튼 클릭 시 해당 EditText를 포커싱 하는 함수
    private fun focusAndShowKeyboard(editText: EditText) {
        editText.text.clear()
        editText.requestFocus()
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    // 다른 곳을 클릭 시 키보드 숨기는 함수
    private fun hideKeyboardIfOutsideEditText(event: MotionEvent, editTexts: List<EditText>) {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        for (editText in editTexts) {
            val location = IntArray(2)
            editText.getLocationOnScreen(location)
            val x = location[0]
            val y = location[1]
            if (event.rawX < x || event.rawX > x + editText.width || event.rawY < y || event.rawY > y + editText.height) {
                editText.clearFocus()
                imm.hideSoftInputFromWindow(editText.windowToken, 0)
            }
        }
    }
}