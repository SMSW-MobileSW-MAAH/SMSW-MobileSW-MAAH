package smsw.maah.presentation.review.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemReviewSearchHospitalBinding
import smsw.maah.domain.model.ReviewSearchHospital
import smsw.maah.util.view.ItemDiffCallback

class ReviewSearchHospitalAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<ReviewSearchHospital, ReviewSearchHospitalAdapter.ViewHolder>(DiffUtil) {

    private var selectedPosition: Int? = null
    inner class ViewHolder(private val binding: ItemReviewSearchHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ReviewSearchHospital, isSelected: Boolean) {
            binding.apply {
                tvReviewSearchHospitalName.text = data.name
                tvReviewSearchHospitalAddressEnter.text = data.address

                vReviewSearchHospital.visibility = if (isSelected) View.VISIBLE else View.INVISIBLE

                root.setOnClickListener {
                    onClick(data.name)
                    setSelectedPosition(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewSearchHospitalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isSelected = selectedPosition == position
        holder.bind(getItem(position), isSelected)
    }

    private fun setSelectedPosition(position: Int) {
        val previousPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousPosition ?: -1)
        notifyItemChanged(selectedPosition ?: -1)
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<ReviewSearchHospital>(
            onItemsTheSame = { old, new -> old.name == new.name },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}

