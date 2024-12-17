package smsw.maah.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import smsw.maah.MainActivity
import smsw.maah.data.entity.User
import smsw.maah.databinding.ActivitySignUpBinding
import smsw.maah.util.base.BindingActivity

class SignUpActivity :
    BindingActivity<ActivitySignUpBinding>({ ActivitySignUpBinding.inflate(it) }) {

    private lateinit var auth: FirebaseAuth
    private lateinit var dataBaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        dataBaseRef = FirebaseDatabase.getInstance().getReference("MAAH")

        binding.btnSignUp.setOnClickListener {
            val nickname = binding.etNickname.text.toString()
            val emailId = binding.etId.text.toString()
            val pwd = binding.etPassword.text.toString()

            if (isPasswordValid(pwd)) {
                auth.createUserWithEmailAndPassword(emailId, pwd)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser? = auth.currentUser
                            firebaseUser?.let { user ->
                                val email = user.email ?: ""
                                val uid = user.uid

                                val profileUpdates = UserProfileChangeRequest.Builder()
                                    .setDisplayName(nickname)
                                    .build()

                                user.updateProfile(profileUpdates)
                                    .addOnCompleteListener { updateTask ->
                                        if (updateTask.isSuccessful) {
                                            val account = User().apply {
                                                this.nickname = nickname
                                                this.emailId = email
                                                this.uid = uid
                                                this.password = pwd
                                            }

                                            dataBaseRef.child("UserAccount").child(uid)
                                                .setValue(account)
                                                .addOnSuccessListener {
                                                    Toast.makeText(
                                                        this,
                                                        "회원가입 및 프로필 설정 성공!",
                                                        Toast.LENGTH_SHORT
                                                    ).show()

                                                    // 이동할 main 정해지면 수정하기
                                                    val intent = Intent(this, MainActivity::class.java)
                                                    startActivity(intent)
                                                    finish()
                                                }
                                                .addOnFailureListener {
                                                    Toast.makeText(
                                                        this,
                                                        "데이터 저장 실패",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                        } else {
                                            Toast.makeText(
                                                this,
                                                "프로필 업데이트 실패",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            }
                        } else {
                            Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(
                    this, "비밀번호는 영문 8자 이상이어야 합니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        val regex = "^[a-zA-Z]{8,}$".toRegex() // 영문만 8자 이상
        return password.matches(regex)
    }
}
