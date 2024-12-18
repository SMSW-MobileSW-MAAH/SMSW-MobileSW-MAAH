package smsw.maah.presentation.review

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import smsw.maah.data.entity.ReviewModel
import smsw.maah.databinding.ActivityReviewBinding
import smsw.maah.presentation.review.adapter.ReviewChecklistAdapter
import smsw.maah.util.base.BindingActivity
import java.io.File

class ReviewActivity :
    BindingActivity<ActivityReviewBinding>({ ActivityReviewBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val reviewId = intent.getStringExtra("reviewId")
        if (!reviewId.isNullOrEmpty()) {
            fetchReviewFromDatabase(reviewId)
        } else {
            Toast.makeText(this, "리뷰 아이디가 없습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun fetchReviewFromDatabase(reviewId: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId == null) {
            Toast.makeText(this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val reviewRef = FirebaseDatabase.getInstance().reference
            .child("reviews")
            .child(userId)
            .child(reviewId)

        reviewRef.get().addOnSuccessListener { snapshot ->
            val reviewData = snapshot.getValue(ReviewModel::class.java)
            if (reviewData != null) {
                displayReviewData(reviewData)
            } else {
                Toast.makeText(this, "리뷰 데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "리뷰 로드 실패: ${it.message}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun displayReviewData(reviewData: ReviewModel) {
        binding.tvHospital.text = reviewData.hospitalName
        binding.tvDoctor.text = reviewData.doctorName
        binding.tvReviewText.text = reviewData.reviewText

        if (!reviewData.imageUrl.isNullOrEmpty()) {
            downloadImageFromFirebase(reviewData.imageUrl, binding.ivUploadPhoto)
        }

        val adapter = ReviewChecklistAdapter(reviewData.reviewChecklist)
        binding.rvChecklist.layoutManager = LinearLayoutManager(this)
        binding.rvChecklist.adapter = adapter
    }


    private fun downloadImageFromFirebase(imagePath: String, imageView: ImageView) {
        val storage = FirebaseStorage.getInstance()
        val storageRef: StorageReference = storage.getReferenceFromUrl(imagePath)

        val localFile = File.createTempFile("tempImage", "jpg")

        storageRef.getFile(localFile)
            .addOnSuccessListener {
                val bitmap = android.graphics.BitmapFactory.decodeFile(localFile.absolutePath)
                imageView.post { // UI 스레드에서 실행
                    imageView.setImageBitmap(bitmap)
                }
                Log.d("나뭉", "이미지 다운로드 성공")
            }
            .addOnFailureListener { exception ->
                Log.d("나뭉", "이미지 다운로드 실패: ${exception.message}")
                Toast.makeText(this, "이미지 다운로드 실패", Toast.LENGTH_SHORT).show()
            }
    }
}
