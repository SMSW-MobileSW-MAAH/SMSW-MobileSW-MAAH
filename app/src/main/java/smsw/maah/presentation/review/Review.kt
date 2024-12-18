package smsw.maah.presentation.review

data class Review(
    val hospitalName: String, // 병원 이름
    val comment: List<String> // 방문자 리뷰
)