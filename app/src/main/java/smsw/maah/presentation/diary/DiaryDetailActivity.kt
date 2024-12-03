package smsw.maah.presentation.diary

import android.os.Bundle
import smsw.maah.databinding.ActivityDiarydetailBinding
import smsw.maah.util.base.BindingActivity

class DiaryDetailActivity :
    BindingActivity<ActivityDiarydetailBinding>({ ActivityDiarydetailBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
