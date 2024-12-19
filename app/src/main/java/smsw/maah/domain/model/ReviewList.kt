package smsw.maah.domain.model

data class ReviewList(
    val hospitalName: String, // 병원 이름
    val comment: List<String>, // 방문자 리뷰
    val reviewId: String
)