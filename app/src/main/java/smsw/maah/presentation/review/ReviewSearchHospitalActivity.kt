package smsw.maah.presentation.review

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import smsw.maah.databinding.ActivityReviewSearchHospitalBinding
import smsw.maah.presentation.review.adapter.ReviewSearchHospitalAdapter
import smsw.maah.presentation.review.viewmodel.ReviewSearchHospitalViewModel
import smsw.maah.util.base.BindingActivity

class ReviewSearchHospitalActivity :
    BindingActivity<ActivityReviewSearchHospitalBinding>({
        ActivityReviewSearchHospitalBinding.inflate(it)
    }) {

    private val viewModel by viewModels<ReviewSearchHospitalViewModel>() // ViewModel 연결
    private lateinit var adapter: ReviewSearchHospitalAdapter // Adapter 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerViewAdapter() // RecyclerView 초기화
        observeHospitalList() // ViewModel 데이터 관찰
        viewModel.loadHospitals() // Mock 데이터 로드
    }

    private fun initRecyclerViewAdapter() {
        adapter = ReviewSearchHospitalAdapter { selectedHospitalName ->
            Toast.makeText(this, "선택된 병원: $selectedHospitalName", Toast.LENGTH_SHORT).show()
        }
        binding.rvReviewSearchHospital.adapter = adapter // RecyclerView에 Adapter 연결
        binding.rvReviewSearchHospital.layoutManager = LinearLayoutManager(this) // 레이아웃 매니저 설정
    }

    private fun observeHospitalList() {
        viewModel.hospitalList.observe(this) { hospitalList ->
            // RecyclerView 데이터 갱신
            adapter.submitList(hospitalList)
        }
    }
}
