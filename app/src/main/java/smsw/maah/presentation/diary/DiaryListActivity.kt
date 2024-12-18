package smsw.maah.presentation.diary

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import smsw.maah.databinding.ActivityDiarylistBinding
import smsw.maah.presentation.review.ReviewAdapter
import smsw.maah.util.base.BindingActivity

class DiaryListActivity :
    BindingActivity<ActivityDiarylistBinding>({ ActivityDiarylistBinding.inflate(it) }){
    private lateinit var diaryAdapter: DiaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiarylistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diaryList = listOf(
            Diary(title = "나뭉이 일기", date = "2024-12-18"),
            Diary(title = "효은이 일기", date = "2024-12-18"),
            Diary(title = "즈비 일기", date = "2024-12-18")
        )

        diaryAdapter = DiaryAdapter(diaryList)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DiaryListActivity)
            adapter = diaryAdapter
        }
    }




}