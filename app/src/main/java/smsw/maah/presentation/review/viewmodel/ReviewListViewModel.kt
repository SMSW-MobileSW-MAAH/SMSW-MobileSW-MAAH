package smsw.maah.presentation.review.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import smsw.maah.domain.model.ReviewList

class ReviewListViewModel : ViewModel() {
    private val _reviewList = MutableLiveData<List<ReviewList>>()
    val reviewList : LiveData<List<ReviewList>> get() = _reviewList

    fun loadReviews(){
        val reviewItems = mutableListOf<ReviewList>()
        reviewItems.add(ReviewList("효은병원", listOf("병원분위기가 편안함을 줘요")))
        reviewItems.add(ReviewList("나뭉병원", listOf("선생님이 상담을 잘해주세요", "진료과정이 체계적이에요")))
        reviewItems.add(ReviewList("즈비병원", listOf("병원분위기가 편안함을 줘요","교통이 편리해요", "치료가 많은 도움이 됐어요")))
        _reviewList.value = reviewItems // LiveData 업데이트
    }
}