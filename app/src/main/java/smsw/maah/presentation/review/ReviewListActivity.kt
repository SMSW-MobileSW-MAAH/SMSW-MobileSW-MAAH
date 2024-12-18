package smsw.maah.presentation.review

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import smsw.maah.databinding.ActivityReviewlistBinding
import smsw.maah.presentation.review.adapter.ReviewListAdapter
import smsw.maah.presentation.review.viewmodel.ReviewListViewModel
import smsw.maah.util.base.BindingActivity
import androidx.activity.viewModels

class ReviewListActivity :
    BindingActivity<ActivityReviewlistBinding>({
        ActivityReviewlistBinding.inflate(it)
    }) {

    private val viewModel by viewModels<ReviewListViewModel>() //view model 연결
    private lateinit var adapter: ReviewListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerViewAdapter() //recycler view 초기화
        observeReviewList() //view model 데이터 관찰
        viewModel.loadReviews() //mock 데이터 로드


    }

    private fun initRecyclerViewAdapter(){
        adapter = ReviewListAdapter { selectedHospitalName ->
            Toast.makeText(this, "선택된 병원: $selectedHospitalName", Toast.LENGTH_SHORT).show()
        }

        binding.rvReviewList.adapter = adapter //recycler view에 adapter 연결
        binding.rvReviewList.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 설정
    }

    private fun observeReviewList(){
        viewModel.reviewList.observe(this) { reviewList ->
            //recycler view 데이터 갱신
            adapter.submitList(reviewList)
        }
    }
}