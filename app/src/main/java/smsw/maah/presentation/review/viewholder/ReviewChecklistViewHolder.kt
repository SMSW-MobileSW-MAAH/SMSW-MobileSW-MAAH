package smsw.maah.presentation.review.viewholder

import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemReviewDetailBinding

class ReviewChecklistViewHolder(
    private val binding: ItemReviewDetailBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(text: String) {
        binding.tvReviewChecklist.text = text
    }
}
