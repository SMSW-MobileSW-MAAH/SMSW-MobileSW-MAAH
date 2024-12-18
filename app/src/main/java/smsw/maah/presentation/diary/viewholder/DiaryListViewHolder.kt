package smsw.maah.presentation.diary.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemDiaryListBinding
import smsw.maah.domain.model.DiaryList

class DiaryListViewHolder (
    private val binding : ItemDiaryListBinding,
    private val onClick : (String) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    fun onBind(data : DiaryList, isSelected : Boolean){
        binding.apply {
            tvDiaryTitle.text = data.title
            tvDiaryDate.text = data.date

            vDiaryList.visibility = if(isSelected) View.VISIBLE else View.INVISIBLE

            root.setOnClickListener {
                onClick(data.title)
            }
        }
    }
}