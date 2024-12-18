package smsw.maah.presentation.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemReviewDetailBinding
import smsw.maah.presentation.review.viewholder.ReviewChecklistViewHolder

class ReviewChecklistAdapter(
    private val checklist: List<String>
) : RecyclerView.Adapter<ReviewChecklistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewChecklistViewHolder {
        val binding = ItemReviewDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReviewChecklistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewChecklistViewHolder, position: Int) {
        holder.bind(checklist[position])
    }

    override fun getItemCount(): Int {
        println("Checklist Size: ${checklist.size}") // 데이터 확인용 로그
        return checklist.size
    }
}
