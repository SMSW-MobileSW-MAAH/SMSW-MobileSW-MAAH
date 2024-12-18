package smsw.maah.presentation.diary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemDiaryListBinding
import smsw.maah.domain.model.DiaryList
import smsw.maah.presentation.diary.viewholder.DiaryListViewHolder
import smsw.maah.presentation.review.viewholder.ReviewSearchHospitalViewHolder
import smsw.maah.util.view.ItemDiffCallback

class DiaryListAdapter (
    private val onClick : (String) -> Unit
): ListAdapter<DiaryList, DiaryListViewHolder> (DiffUtil){

    private var selectedPosition : Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int) : DiaryListViewHolder{
        val binding = ItemDiaryListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return DiaryListViewHolder(binding) { selectedTitle ->
            val previousPosition = selectedPosition
            selectedPosition = currentList.indexOfFirst { it.title == selectedTitle }

            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)

            onClick(selectedTitle)
        }
    }

    override fun onBindViewHolder(holder: DiaryListViewHolder, position: Int) {
        val isSelected = position == selectedPosition
        holder.onBind(getItem(position), isSelected)
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<DiaryList>(
            onItemsTheSame = { old, new -> old.title == new.title },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}

