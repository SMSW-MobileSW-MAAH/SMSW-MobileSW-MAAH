package smsw.maah.presentation.review

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val recyclerView: RecyclerView = binding.rvReviewList
        recyclerView.layoutManager = LinearLayoutManager(this)

        //더미
        val reviewList = listOf(
            Review(hospitalName = "효은병원", comment = listOf("병원 분위기가 편안함을 줘요")),
            Review(hospitalName = "나뭉병원", comment = listOf("교통편이 편리해요", "진료 과정이 체계적이에요")),
        )

    // Adapter 설정
        val adapter = ReviewAdapter(reviewList)
        recyclerView.adapter = adapter

    }
}