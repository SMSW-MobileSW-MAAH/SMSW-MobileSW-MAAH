package smsw.maah.presentation.diary

import android.app.DatePickerDialog
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import smsw.maah.R
import smsw.maah.databinding.ActivityWritediaryBinding
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

            // 로그 추가
            println("Icon clicked: Attempting to show DatePickerDialog")

            // DatePickerDialog 생성
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // 선택한 날짜를 TextView에 반영
                val selectedDate = String.format("%d.%02d.%02d", selectedYear, selectedMonth + 1, selectedDay)
                diaryDateText.text = selectedDate
                val background = diaryDateBox.background as GradientDrawable
                background.setColor(ContextCompat.getColor(this, R.color.yellow)) // 노란색으로 변경

            }, year, month, day).show()
        }
    }


}