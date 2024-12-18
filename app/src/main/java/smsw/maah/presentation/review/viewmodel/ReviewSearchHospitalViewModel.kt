package smsw.maah.presentation.review.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import smsw.maah.domain.model.ReviewSearchHospital

class ReviewSearchHospitalViewModel : ViewModel() {

    private val _hospitalList = MutableLiveData<List<ReviewSearchHospital>>()
    val hospitalList: LiveData<List<ReviewSearchHospital>> get() = _hospitalList

    fun loadHospitals() {
        _hospitalList.value = listOf(
            ReviewSearchHospital("서울시립영보정신요양원", "경기도 용인시 처인구 이동면 이원로 483"),
            ReviewSearchHospital("다른 병원", "서울특별시 강남구 대치동"),
            ReviewSearchHospital("병원2", "경기도 용인시 처인구 이동면 이원로 483"),
            ReviewSearchHospital("병원3", "서울특별시 강남구 대치동"),
            ReviewSearchHospital("병원4", "경기도 용인시 처인구 이동면 이원로 483"),
            ReviewSearchHospital("병원5", "서울특별시 강남구 대치동"),

            )
    }
}
