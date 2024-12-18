package smsw.maah.presentation.mypage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import smsw.maah.R
import smsw.maah.databinding.ActivityDiaryBinding
import smsw.maah.databinding.ActivityMyPageBinding
import smsw.maah.util.base.BindingActivity

class MyPageActivity : BindingActivity<ActivityMyPageBinding>({ ActivityMyPageBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}