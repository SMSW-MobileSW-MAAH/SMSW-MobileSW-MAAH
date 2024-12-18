package smsw.maah.presentation.diary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import smsw.maah.domain.model.DiaryList

class DiarylistViewModel  : ViewModel(){
    private val _diaryList = MutableLiveData<List<DiaryList>>()
    val diaryList : LiveData<List<DiaryList>> get() = _diaryList

    fun loadDiarys(){
        _diaryList.value = listOf(
            DiaryList("나뭉이 일기", "2024-12-18"),
            DiaryList("효은이 일기", "2024-12-18"),
            DiaryList("즈비 일기", "2024-12-18"),
            DiaryList("연우 일기", "2024-12-18"),
            DiaryList("수진이 일기", "2024-12-18"),
            DiaryList("니나 일기", "2024-12-18"),
        )
    }
}