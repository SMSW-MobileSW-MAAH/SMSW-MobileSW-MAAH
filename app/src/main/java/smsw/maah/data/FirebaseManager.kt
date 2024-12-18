package smsw.maah.data

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import smsw.maah.data.entity.ReviewModel

class FirebaseManager(private val activity: Activity) {
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
    private val storageRef: StorageReference = storage.reference
    private val databaseRef = FirebaseDatabase.getInstance().reference
    private val currentUser = FirebaseAuth.getInstance().currentUser

    // 이미지 업로드 후 리뷰 데이터 저장
    fun uploadImageAndSaveReview(imageUri: Uri?, reviewData: ReviewModel, callback: (String?) -> Unit) {
        val userId = currentUser?.uid
        if (userId == null) {
            Toast.makeText(activity, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show()
            callback(null)
            return
        }

        if (imageUri != null) {
            val imageRef = storageRef.child("reviews/$userId/${System.currentTimeMillis()}_review.jpg")
            val uploadTask = imageRef.putFile(imageUri)

            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()
                    val finalReviewData = reviewData.copy(imageUrl = imageUrl)
                    saveReviewToDatabase(finalReviewData, userId, callback)
                }
            }.addOnFailureListener {
                Toast.makeText(activity, "이미지 업로드 실패: ${it.message}", Toast.LENGTH_SHORT).show()
                callback(null)
            }
        } else {
            saveReviewToDatabase(reviewData, userId, callback)
        }
    }

    private fun saveReviewToDatabase(reviewData: ReviewModel, userId: String, callback: (String?) -> Unit) {
        val reviewRef = databaseRef.child("reviews").child(userId).push()
        reviewRef.setValue(reviewData)
            .addOnSuccessListener {
                callback(reviewRef.key) // 리뷰 아이디 반환
            }
            .addOnFailureListener {
                Toast.makeText(activity, "리뷰 저장 실패: ${it.message}", Toast.LENGTH_SHORT).show()
                callback(null)
            }
    }
}