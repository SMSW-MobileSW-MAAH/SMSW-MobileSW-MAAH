package smsw.maah.presentation.review

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import smsw.maah.databinding.ActivityReviewSearchHospitalBinding
import smsw.maah.presentation.review.adapter.ReviewSearchHospitalAdapter
import smsw.maah.presentation.review.viewmodel.ReviewSearchHospitalViewModel
import smsw.maah.util.base.BindingActivity

class ReviewSearchHospitalActivity :
    BindingActivity<ActivityReviewSearchHospitalBinding>(ActivityReviewSearchHospitalBinding::inflate) {

    private val viewModel by viewModels<ReviewSearchHospitalViewModel>()
    private lateinit var adapter: ReviewSearchHospitalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerViewAdapter()
        observeHospitalList()
        viewModel.loadHospitalsFromServer() // 서버 데이터 호출

    }

    private fun initRecyclerViewAdapter() {
        adapter = ReviewSearchHospitalAdapter { selectedHospitalName ->
            Toast.makeText(this, "선택된 병원: $selectedHospitalName", Toast.LENGTH_SHORT).show()
        }
        binding.rvReviewSearchHospital.adapter = adapter
        binding.rvReviewSearchHospital.layoutManager = LinearLayoutManager(this)

    }

    private fun observeHospitalList() {
        viewModel.hospitalList.observe(this) { hospitalList ->
            adapter.submitList(hospitalList) // RecyclerView 데이터 갱신
        }
    }
}
