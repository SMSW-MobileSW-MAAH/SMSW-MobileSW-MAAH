package smsw.maah.presentation.diary

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import smsw.maah.R
import smsw.maah.databinding.ActivityDiaryBinding
import smsw.maah.databinding.ActivityDiaryEmptyBinding
import smsw.maah.util.base.BindingActivity

class DiaryEmptyActivity : BindingActivity<ActivityDiaryEmptyBinding>({ ActivityDiaryEmptyBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.floatingBtn.setOnClickListener{ //플로팅 버튼 클릭 시 WriteDiaryActivity로 이동
            val intent = Intent(this, WriteDiaryActivity::class.java)
            startActivity(intent)
        }
    }
}