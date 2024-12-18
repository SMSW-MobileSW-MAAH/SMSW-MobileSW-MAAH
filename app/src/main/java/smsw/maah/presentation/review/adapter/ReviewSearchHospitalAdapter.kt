package smsw.maah.presentation.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemReviewSearchHospitalBinding
import smsw.maah.domain.model.ReviewSearchHospital
import smsw.maah.presentation.review.viewholder.ReviewSearchHospitalViewHolder
import smsw.maah.util.view.ItemDiffCallback

class ReviewSearchHospitalAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<ReviewSearchHospital, ReviewSearchHospitalViewHolder>(DiffUtil) {

    private var selectedPosition: Int = RecyclerView.NO_POSITION // 선택된 아이템 위치

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewSearchHospitalViewHolder {
        val binding = ItemReviewSearchHospitalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReviewSearchHospitalViewHolder(binding) { selectedName ->
            val previousPosition = selectedPosition
            selectedPosition = currentList.indexOfFirst { it.name == selectedName }

            // 선택 상태 업데이트
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)

            onClick(selectedName)
        }
    }

    override fun onBindViewHolder(holder: ReviewSearchHospitalViewHolder, position: Int) {
        val isSelected = position == selectedPosition
        holder.onBind(getItem(position), isSelected)
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<ReviewSearchHospital>(
            onItemsTheSame = { old, new -> old.name == new.name },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
