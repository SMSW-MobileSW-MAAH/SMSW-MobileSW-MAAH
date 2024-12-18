package smsw.maah.presentation.diary

import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import smsw.maah.databinding.ActivityDiarydetailBinding
import smsw.maah.util.base.BindingActivity

class DiaryDetailActivity :
    BindingActivity<ActivityDiarydetailBinding>({ ActivityDiarydetailBinding.inflate(it) }) {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("diaries")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDiarydetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diaryId = intent.getStringExtra("DIARY_ID") // 인텐트로 전달된 일기 ID
        if (diaryId != null) {
            loadDiaryDetail(diaryId)
        } else {
            Log.e("DiaryDetail", "일기 ID가 없습니다.")
            finish()
        }
    }

    private fun loadDiaryDetail(diaryId: String) {
        database.child(diaryId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val title = snapshot.child("title").getValue(String::class.java)
                val date = snapshot.child("date").getValue(String::class.java)
                val content = snapshot.child("content").getValue(String::class.java)

                binding.tvDetailTitle.text = title ?: "제목 없음"
                binding.tvDetailDate.text = date ?: "날짜 없음"
                binding.tvDetailText.text = content ?: "내용이 없습니다."
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DiaryDetail", "Firebase 데이터 로드 실패: ${error.message}")
            }
        })
    }
}
