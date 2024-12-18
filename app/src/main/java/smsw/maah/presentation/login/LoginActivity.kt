package smsw.maah.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import smsw.maah.MainActivity
import smsw.maah.databinding.ActivityLoginBinding
import smsw.maah.presentation.review.ReviewWriteActivity
import smsw.maah.util.base.BindingActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {

    private lateinit var auth: FirebaseAuth
    private lateinit var dataBaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        dataBaseRef = FirebaseDatabase.getInstance().getReference("MAAH")

        binding.btnLogin.setOnClickListener {
            val emailId = binding.etId.text.toString()
            val pwd = binding.etPassword.text.toString()

            auth.signInWithEmailAndPassword(emailId, pwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "로그인 성공!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this, ReviewWriteActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "로그인 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}
