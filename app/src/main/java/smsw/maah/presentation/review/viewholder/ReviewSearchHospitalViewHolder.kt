package smsw.maah.presentation.review.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemReviewSearchHospitalBinding
import smsw.maah.domain.model.ReviewSearchHospital


class ReviewSearchHospitalViewHolder(
    private val binding: ItemReviewSearchHospitalBinding,
    private val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: ReviewSearchHospital, isSelected: Boolean) {
        binding.apply {
            tvReviewSearchHospitalName.text = data.name
            tvReviewSearchHospitalAddressEnter.text = data.address

            vReviewSearchHospital.visibility = if (isSelected) View.VISIBLE else View.INVISIBLE

            root.setOnClickListener {
                onClick(data.name)
            }
        }
    }
}
