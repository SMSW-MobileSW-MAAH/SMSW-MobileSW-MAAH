package smsw.maah.presentation.review

import android.os.Bundle
import smsw.maah.R
import smsw.maah.databinding.ActivityReviewWriteBinding
import smsw.maah.util.base.BindingActivity

class ReviewWriteActivity :
    BindingActivity<ActivityReviewWriteBinding>({ ActivityReviewWriteBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hospitalName = intent.getStringExtra("hospital_name") ?: "병원 이름 없음"

        binding.tvHospitalNameWrite.text = hospitalName

        binding.tvHospitalNameWrite.apply {
            text = hospitalName
            setTextColor(
                getColor(R.color.grayscale8)

            )
        }
    }
}
