package smsw.maah.presentation.review

import android.os.Bundle
import smsw.maah.databinding.ActivityReviewlistBinding
import smsw.maah.util.base.BindingActivity

class ReviewListActivity :
    BindingActivity<ActivityReviewlistBinding>({ ActivityReviewlistBinding.inflate(it) }){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.searchHospital.apply {
            isIconified = false  // SearchView를 확장된 상태로 설정
            clearFocus()         // 포커스를 제거하여 키보드 자동 표시 방지
        }
    }
}