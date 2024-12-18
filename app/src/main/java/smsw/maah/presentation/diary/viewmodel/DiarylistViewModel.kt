package smsw.maah.presentation.diary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import smsw.maah.domain.model.DiaryList

class DiarylistViewModel  : ViewModel(){
    private val _diaryList = MutableLiveData<List<DiaryList>>()
    val diaryList : LiveData<List<DiaryList>> get() = _diaryList


    private val database: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("diaries")

    init {
        loadDiaries()
    }

    fun loadDiaries(){
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val diaryItems = mutableListOf<DiaryList>()
                for (diarySnapshot in snapshot.children) {
                    val diary = diarySnapshot.getValue(DiaryList::class.java)
                    if (diary != null) {
                        diaryItems.add(diary.copy(id = diarySnapshot.key ?: ""))
                    }
                }
                _diaryList.value = diaryItems // LiveData 업데이트
            }

            override fun onCancelled(error: DatabaseError) {
                // Firebase 오류 처리
                println("Firebase 데이터 로드 실패: ${error.message}")
            }
        })
    }
}