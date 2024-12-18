package smsw.maah.data

import retrofit2.http.GET
import retrofit2.http.Query
import smsw.maah.data.dto.HospitalResponseDto

interface HospitalService {
    @GET("3049990/v1/uddi:0fcb2f4e-b1ff-442e-a4d0-a328e1b22050")
    suspend fun getFacilities(
        @Query("page") page: Int = 1,
        @Query("perPage") perPage: Int = 5
    ): HospitalResponseDto
}
