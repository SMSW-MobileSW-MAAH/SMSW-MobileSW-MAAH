package smsw.maah.presentation.diary.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ItemDiaryListBinding
import smsw.maah.domain.model.DiaryList
import smsw.maah.presentation.diary.viewholder.DiaryListViewHolder
import smsw.maah.util.view.ItemDiffCallback

class DiaryListAdapter (
    private val onClick : (String) -> Unit //클릭 시 id 전달
): ListAdapter<DiaryList, DiaryListViewHolder> (DiffUtil){

    private var selectedPosition : Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int) : DiaryListViewHolder{
        val binding = ItemDiaryListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return DiaryListViewHolder(binding) { selectedDiaryId ->
            val previousPosition = selectedPosition

            selectedPosition = currentList.indexOfFirst { it.id == selectedDiaryId }

            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)

            onClick(selectedDiaryId)
        }
    }


    override fun onBindViewHolder(holder: DiaryListViewHolder, position: Int) {
        val isSelected = position == selectedPosition
        val item = getItem(position)
        holder.onBind(getItem(position), isSelected)
        holder.itemView.setOnClickListener {
            Log.d("DiaryListAdapter", "아이템 클릭: ${item.id}")
            onClick(item.id) // item.id가 정확히 전달되는지 확인
        }
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<DiaryList>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}

