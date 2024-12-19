package smsw.maah.presentation.review

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
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

        binding.searchHospital.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let{ filterReviews(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let{ filterReviews(it) }
                return false
            }
        })

    }

    private fun initRecyclerViewAdapter(){
        adapter = ReviewListAdapter { reviewId ->
            val intent = Intent(this, ReviewActivity::class.java)
            Log.d("ReviewListActivity", "Review ID being passed: $reviewId")
            intent.putExtra("reviewId", reviewId)
            startActivity(intent)
        }

        binding.rvReviewList.adapter = adapter
        binding.rvReviewList.layoutManager = LinearLayoutManager(this)
    }

    private fun observeReviewList(){
        viewModel.reviewList.observe(this) { reviewList ->
            //recycler view 데이터 갱신
            adapter.submitList(reviewList)
        }
    }

    private fun filterReviews(query: String) {
        viewModel.reviewList.value?.let { reviewList ->
            val filteredList = reviewList.filter { it.hospitalName.contains(query, ignoreCase = true) }
            adapter.submitList(filteredList)
        }
    }
}