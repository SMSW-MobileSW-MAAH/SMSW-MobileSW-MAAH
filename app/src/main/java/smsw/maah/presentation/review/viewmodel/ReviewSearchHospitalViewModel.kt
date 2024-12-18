package smsw.maah.presentation.review.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import smsw.maah.data.ServicePool
import smsw.maah.domain.model.ReviewSearchHospital


class ReviewSearchHospitalViewModel : ViewModel() {
    private val _originalHospitalList = mutableListOf<ReviewSearchHospital>()
    private val _hospitalList = MutableLiveData<List<ReviewSearchHospital>>()
    val hospitalList: LiveData<List<ReviewSearchHospital>> get() = _hospitalList

    fun loadHospitalsFromServer() {
        viewModelScope.launch {
            try {
                val response = ServicePool.facilityService.getFacilities(page = 1, perPage = 8)

                val mappedList = response.data.map { dto ->
                    ReviewSearchHospital(
                        name = dto.name,
                        address = dto.address
                    )
                }

                _originalHospitalList.clear()
                _originalHospitalList.addAll(mappedList)
                _hospitalList.value = emptyList()
            } catch (e: Exception) {
                Log.e("ViewModel", "에러 발생: ${e.message}", e)
            }
        }
    }

    fun filterHospitals(query: String) {
        if (query.isEmpty()) {
            _hospitalList.value = emptyList()
        } else {
            _hospitalList.value = _originalHospitalList.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }
}
