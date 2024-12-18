package smsw.maah.presentation.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemReviewSearchHospitalBinding
import smsw.maah.domain.model.ReviewSearchHospital

class ReviewSearchHospitalAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<ReviewSearchHospital, ReviewSearchHospitalAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ReviewSearchHospital>() {
            override fun areItemsTheSame(
                oldItem: ReviewSearchHospital,
                newItem: ReviewSearchHospital
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: ReviewSearchHospital,
                newItem: ReviewSearchHospital
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemReviewSearchHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ReviewSearchHospital) {
            binding.apply {
                tvReviewSearchHospitalName.text = data.name
                tvReviewSearchHospitalAddressEnter.text = data.address

                root.setOnClickListener {
                    onClick(data.name)
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
        holder.bind(getItem(position))
    }
}
