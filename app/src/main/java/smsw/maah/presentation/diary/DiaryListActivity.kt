package smsw.maah.presentation.diary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import smsw.maah.databinding.ActivityDiarylistBinding
import smsw.maah.domain.model.DiaryList
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
        viewModel.loadDiarys() //mock 데이터 로드
    }

    private fun initRecyclerViewAdapter(){
        adapter = DiaryListAdapter { selectedDiaryTitle ->
            Toast.makeText(this, "선택된 일기: $selectedDiaryTitle", Toast.LENGTH_SHORT).show()
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