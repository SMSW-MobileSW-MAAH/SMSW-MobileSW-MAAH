package smsw.maah.presentation.review

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import smsw.maah.databinding.ActivityReviewBinding
import smsw.maah.presentation.review.adapter.ReviewChecklistAdapter
import smsw.maah.util.base.BindingActivity

class ReviewActivity :
    BindingActivity<ActivityReviewBinding>({ ActivityReviewBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hospitalName = intent.getStringExtra("hospitalName")
        val doctorName = intent.getStringExtra("doctorName")
        val reviewChecklist = intent.getStringArrayExtra("reviewChecklist")?.toList() ?: emptyList()
        val reviewText = intent.getStringExtra("reviewText")
        val imageUrl = intent.getStringExtra("imageUrl")

        binding.tvHospital.text = hospitalName
        binding.tvDoctor.text = doctorName
        binding.tvReviewText.text = reviewText

        println("Review Checklist: $reviewChecklist")

        val adapter = ReviewChecklistAdapter(reviewChecklist)
        binding.rvChecklist.layoutManager = LinearLayoutManager(this)
        binding.rvChecklist.adapter = adapter
    }
}
