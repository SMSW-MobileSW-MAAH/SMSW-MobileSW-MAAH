package smsw.maah.presentation.review

data class Review(
    val hospitalName: String, // 병원 이름
    val rating: Int,          // 별점 (예: 1~5점)
    val comment: String       // 후기 내용
)