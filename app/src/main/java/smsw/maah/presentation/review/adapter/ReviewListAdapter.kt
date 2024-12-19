package smsw.maah.presentation.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemDiaryListBinding
import smsw.maah.databinding.ItemReviewListBinding
import smsw.maah.domain.model.ReviewList
import smsw.maah.domain.model.ReviewSearchHospital
import smsw.maah.presentation.diary.viewholder.DiaryListViewHolder
import smsw.maah.presentation.review.viewholder.ReviewListViewHolder
import smsw.maah.presentation.review.viewholder.ReviewSearchHospitalViewHolder
import smsw.maah.util.view.ItemDiffCallback

class ReviewListAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<ReviewList, ReviewListViewHolder>(DiffUtil) {

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListViewHolder {
        val binding = ItemReviewListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewListViewHolder(binding) { selectedReviewId ->
            val position = currentList.indexOfFirst { it.reviewId == selectedReviewId }  // reviewId로 선택된 아이템 찾기
            val reviewId = getItem(position).reviewId
            onClick(reviewId)
        }
    }

    override fun onBindViewHolder(holder: ReviewListViewHolder, position: Int) {
        val isSelected = position == selectedPosition
        holder.onBind(getItem(position), isSelected)
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<ReviewList>(
            onItemsTheSame = { old, new -> old.hospitalName == new.hospitalName && old.reviewId == new.reviewId },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}

