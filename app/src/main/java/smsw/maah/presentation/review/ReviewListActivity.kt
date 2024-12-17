package smsw.maah.presentation.review

import android.os.Bundle
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

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        //더미
        val reviewList = listOf(
            Review("서울병원", 5, "좋은 병원입니다."),
            Review("부산병원", 4, "친절했어요."),
            Review("대전병원", 3, "보통이었습니다.")
        )

    // Adapter 설정
        val adapter = ReviewAdapter(reviewList)
        recyclerView.adapter = adapter

    }
}