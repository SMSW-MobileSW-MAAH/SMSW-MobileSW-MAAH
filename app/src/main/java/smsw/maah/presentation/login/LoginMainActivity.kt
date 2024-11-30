package smsw.maah.presentation.login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import smsw.maah.R
import smsw.maah.databinding.ActivityLoginMainBinding
import smsw.maah.util.base.BindingActivity

class LoginMainActivity :
    BindingActivity<ActivityLoginMainBinding>({ ActivityLoginMainBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}