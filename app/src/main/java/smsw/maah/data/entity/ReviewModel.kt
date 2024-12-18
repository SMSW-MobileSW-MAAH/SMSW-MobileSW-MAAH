package smsw.maah.data.entity

data class ReviewModel(
    val hospitalName: String = "",
    val doctorName: String = "",
    val reviewChecklist: List<String> = emptyList(),
    val reviewText: String = "",
    val imageUrl: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
