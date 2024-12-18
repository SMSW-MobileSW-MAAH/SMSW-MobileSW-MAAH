package smsw.maah.presentation.diary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.DiaryListItemBinding

class DiaryAdapter(private val diaryList: List<Diary>) :
    RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {

    // ViewHolder 클래스
    inner class DiaryViewHolder(private val binding: DiaryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(diary: Diary) {
            binding.tvDiaryTitle.text = diary.title
            binding.tvDiaryDate.text = diary.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val binding = DiaryListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DiaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(diaryList[position])
    }

    override fun getItemCount(): Int = diaryList.size
}