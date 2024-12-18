package smsw.maah.domain.model

data class DiaryList(
    val id : String,
    val title: String,
    val date: String,
) {
    constructor() : this("", "", "")
}