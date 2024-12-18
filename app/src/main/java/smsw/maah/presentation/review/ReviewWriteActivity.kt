package smsw.maah.presentation.review

import android.os.Bundle
import androidx.core.app.ActivityCompat
import smsw.maah.databinding.ActivityReviewWriteBinding
import smsw.maah.util.base.BindingActivity
import android.Manifest
import android.app.Activity
import android.content.Intent
import smsw.maah.data.FirebaseManager
import android.net.Uri
import android.widget.CheckBox
import android.widget.Toast
import smsw.maah.data.entity.ReviewModel


class ReviewWriteActivity :
    BindingActivity<ActivityReviewWriteBinding>({ ActivityReviewWriteBinding.inflate(it) }) {
    private lateinit var firebaseManager: FirebaseManager
    private var imageUri: Uri? = null
    private var reviewData: ReviewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
        firebaseManager = FirebaseManager(this)

        binding.ivUploadPhoto.setOnClickListener {
            openImagePicker()
        }

        binding.btnSubmitReview.setOnClickListener {
            saveReview { reviewId ->
                val intent = Intent(this, ReviewActivity::class.java)
                intent.putExtra("reviewId", reviewId)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_REQUEST && resultCode == Activity.RESULT_OK && data?.data != null) {
            imageUri = data.data
            binding.ivUploadPhoto.setImageURI(imageUri) // 이미지 미리 보기
        }
    }

    private fun saveReview(callback: (String?) -> Unit) {
        val hospitalName = binding.tvHospitalNameWrite.text.toString()
        val doctorName = binding.etHospitalName.text.toString()
        val reviewText = binding.etReviewInput.text.toString()

        val selectedChecklist = mutableListOf<String>()
        val checkBoxGroup = binding.llReviewChecklist
        for (i in 0 until checkBoxGroup.childCount) {
            val checkBox = checkBoxGroup.getChildAt(i) as CheckBox
            if (checkBox.isChecked) {
                selectedChecklist.add(checkBox.text.toString())
            }
        }

        if (reviewText.isBlank()) {
            Toast.makeText(this, "필수 항목을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        reviewData = ReviewModel(
            hospitalName = hospitalName,
            doctorName = doctorName,
            reviewChecklist = selectedChecklist,
            reviewText = reviewText
        )

        firebaseManager.uploadImageAndSaveReview(imageUri, reviewData!!) { reviewId ->
            callback(reviewId) // 리뷰 아이디를 콜백으로 반환
        }
    }


    companion object {
        const val IMAGE_PICK_REQUEST = 1001
    }
}