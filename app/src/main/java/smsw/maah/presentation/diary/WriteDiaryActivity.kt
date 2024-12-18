package smsw.maah.presentation.diary

import android.app.DatePickerDialog
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import smsw.maah.R
import smsw.maah.databinding.ActivityWritediaryBinding
import smsw.maah.presentation.dialog.ConfirmDialog
import smsw.maah.util.base.BindingActivity
import java.util.Calendar

class WriteDiaryActivity :
    BindingActivity<ActivityWritediaryBinding>({ ActivityWritediaryBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val diaryDateBox = binding.diaryDateBox
        val diaryDateText = binding.tvDiaryDate

        diaryDateText.setOnClickListener {
            println("diaryDateBox clicked")
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // DatePickerDialog 생성
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // 선택한 날짜를 TextView에 반영
                val selectedDate = String.format("%d.%02d.%02d", selectedYear, selectedMonth + 1, selectedDay)
                diaryDateText.text = selectedDate
                val background = diaryDateBox.background as GradientDrawable
                background.setColor(ContextCompat.getColor(this, R.color.yellow)) // 노란색으로 변경

            }, year, month, day).show()
        }

        // 완료 버튼 클릭 시 다이얼로그 표시
        binding.confirmButton.setOnClickListener {
            showCustomDialog(
                title="일기를 저장하시겠어요?",
                subTitle = " ",
                confirmButtonText = "그대로 저장할게요",
                cancelButtonText = "다시 한번 확인할래요",
                onConfirm = {
                    Log.d("WriteDiaryActivity", "일기 저장됨")
                },
                onCancel = {
                    Log.d("WriteDiaryActivity", "저장 취소됨")
                }
            )
        }

        // 뒤로가기 버튼 클릭 시 다이얼로그 표시
        // 뒤로가기 버튼 동작 처리
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showCustomDialog(
                    title = "작성을 정말 취소하시겠어요?\n작성한 내용은 저장되지 않아요.",
                    subTitle = " ",
                    confirmButtonText = "취소할게요" ,
                    cancelButtonText = "다시 한번 확인할래요",
                    onConfirm = {
                        finish() // 액티비티 종료
                    },
                    onCancel = {
                        Log.d("WriteDiaryActivity", "저장 취소됨")
                    }
                )
            }
        })
    }

    private fun showCustomDialog(
        title: String,
        subTitle: String,
        confirmButtonText: String = "확인",  // 기본값 설정
        cancelButtonText: String = "취소",  // 기본값 설정
        onConfirm: () -> Unit,
        onCancel: () -> Unit
    ) {
        val dialog = ConfirmDialog(
            context = this,
            message = title,
            subMessage = subTitle,
            confirmButtonText = confirmButtonText,
            cancelButtonText = cancelButtonText,
            onConfirm = onConfirm,
            onCancel = onCancel
        )
        dialog.show()
    }

}