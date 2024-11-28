package smsw.maah.presentation.diary

import android.os.Bundle
import smsw.maah.databinding.ActivityDiaryBinding
import smsw.maah.util.base.BindingActivity

class WriteDiaryActivity  : BindingActivity<ActivityDiaryBinding>({ ActivityDiaryBinding.inflate(it) }){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}