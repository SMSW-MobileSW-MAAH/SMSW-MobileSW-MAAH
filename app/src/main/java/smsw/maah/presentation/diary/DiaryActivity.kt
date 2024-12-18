package smsw.maah.presentation.diary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import smsw.maah.databinding.ActivityDiaryBinding
import smsw.maah.util.base.BindingActivity

class DiaryActivity : BindingActivity<ActivityDiaryBinding>({ ActivityDiaryBinding.inflate(it) }) {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("diaries")
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        checkDiaryExists()
    }
    private fun checkDiaryExists() {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            // 사용자가 로그인되지 않은 경우
            finish() // 현재 액티비티 종료
            return
        }

        database.orderByChild("userId").equalTo(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // 일기가 존재하면 DiaryListActivity로 이동
                        Log.d("DiaryActivity", "일기 존재함")
                        val intent = Intent(this@DiaryActivity, DiaryListActivity::class.java)
                        startActivity(intent)
                    } else {
                        // 일기가 없으면 DiaryEmptyActivity로 이동
                        Log.d("DiaryActivity", "일기 없음")
                        val intent = Intent(this@DiaryActivity, DiaryEmptyActivity::class.java)
                        startActivity(intent)
                    }
                    finish() // 현재 액티비티 종료
                }

                override fun onCancelled(error: DatabaseError) {
                    // Firebase 데이터 로드 실패 처리
                    finish() // 실패 시에도 현재 액티비티 종료
                }
            })
    }
}
