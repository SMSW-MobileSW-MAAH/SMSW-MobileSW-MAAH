package smsw.maah.presentation.review.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import smsw.maah.domain.model.ReviewList
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReviewListViewModel : ViewModel() {
    private val _reviewList = MutableLiveData<List<ReviewList>>()
    val reviewList : LiveData<List<ReviewList>> get() = _reviewList

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    fun loadReviews() {
        database.child("reviews").get().addOnSuccessListener { snapshot ->
            val reviewItems = mutableListOf<ReviewList>()

            snapshot.children.forEach { userSnapshot ->
                userSnapshot.children.forEach { reviewSnapshot ->
                    val reviewId = reviewSnapshot.key ?: ""
                    val hospitalName =
                        reviewSnapshot.child("hospitalName").getValue(String::class.java)
                            ?: "병원 정보 없음"
                    val reviewChecklist = reviewSnapshot.child("reviewChecklist").children.map {
                        it.getValue(String::class.java) ?: ""
                    }

                    reviewItems.add(ReviewList(hospitalName, reviewChecklist, reviewId))
                }
            }

            _reviewList.value = reviewItems // LiveData에 데이터 업데이트

        }
    }
}