package smsw.maah.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HospitalResponseDto(
    @SerialName("page")
    val page: Int,
    @SerialName("perPage")
    val perPage: Int,
    @SerialName("totalCount")
    val totalCount: Int,
    @SerialName("currentCount")
    val currentCount: Int,
    @SerialName("matchCount")
    val matchCount: Int,
    @SerialName("data")
    val data: List<HospitalList>
)

@Serializable
data class HospitalList(
    @SerialName("광역시/도 시군구")
    val region: String,
    @SerialName("기관(시설)명")
    val name: String,
    @SerialName("주소")
    val address: String,
    @SerialName("전화번호")
    val phone: String,
    @SerialName("홈페이지")
    val homepage: String
)
