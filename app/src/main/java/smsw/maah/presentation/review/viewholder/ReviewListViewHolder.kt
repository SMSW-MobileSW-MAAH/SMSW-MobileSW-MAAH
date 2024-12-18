package smsw.maah.presentation.review.viewholder

import android.content.ClipData.Item
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemReviewListBinding
import smsw.maah.domain.model.ReviewList

class ReviewListViewHolder(
    private val binding : ItemReviewListBinding,
    private val onClick : (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data : ReviewList,  isSelected: Boolean){
        binding.apply {
            tvHospitalName.text = data.hospitalName
            tvReview.text = data.comment.joinToString("\n")


            root.setOnClickListener {
                onClick(data.hospitalName)
            }
        }
    }

}