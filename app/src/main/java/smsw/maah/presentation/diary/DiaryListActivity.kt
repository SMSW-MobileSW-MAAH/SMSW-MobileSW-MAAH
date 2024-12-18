package smsw.maah.presentation.diary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import smsw.maah.databinding.ActivityDiarylistBinding
import smsw.maah.presentation.diary.adapter.DiaryListAdapter
import smsw.maah.presentation.diary.viewmodel.DiarylistViewModel
import smsw.maah.util.base.BindingActivity

class DiaryListActivity :
    BindingActivity<ActivityDiarylistBinding>({
        ActivityDiarylistBinding.inflate(it)
    }) {

    private val viewModel by viewModels<DiarylistViewModel>() //view model 연결
    private lateinit var  adapter : DiaryListAdapter //adapter 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerViewAdapter() //recycler view 초기화
        observeDiaryList() //view model 데이터 관찰
        viewModel.loadDiaries() //mock 데이터 로드

        binding.floatingBtn.setOnClickListener{ //플로팅 버튼 클릭 시 WriteDiaryActivity로 이동
            val intent = Intent(this, WriteDiaryActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initRecyclerViewAdapter(){
        adapter = DiaryListAdapter { diaryId ->
            Log.d("DiaryListActivity", "전달된 Diary ID: $diaryId")
            val intent = Intent(this, DiaryDetailActivity::class.java)
            intent.putExtra("DIARY_ID", diaryId) // 선택한 일기의 ID 전달
            startActivity(intent)
        }
        binding.rvDiaryList.adapter = adapter //recycler view에 adapter 연결
        binding.rvDiaryList.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 설정
    }

    private fun observeDiaryList(){
        viewModel.diaryList.observe(this) { diaryList ->
            //recycler view 데이터 갱신
            adapter.submitList(diaryList)
        }
    }




}