package smsw.maah.presentation.review

import android.os.Bundle
import smsw.maah.databinding.ActivityReviewSearchHospitalBinding
import smsw.maah.util.base.BindingActivity


class ReviewSearchHospitalActivity :
    BindingActivity<ActivityReviewSearchHospitalBinding>({
        ActivityReviewSearchHospitalBinding.inflate(
            it
        )
    }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
