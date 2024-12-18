package smsw.maah.presentation.review

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import smsw.maah.databinding.ActivityReviewSearchHospitalBinding
import smsw.maah.presentation.review.adapter.ReviewSearchHospitalAdapter
import smsw.maah.presentation.review.viewmodel.ReviewSearchHospitalViewModel
import smsw.maah.util.base.BindingActivity

class ReviewSearchHospitalActivity :
    BindingActivity<ActivityReviewSearchHospitalBinding>(ActivityReviewSearchHospitalBinding::inflate) {

    private val viewModel by viewModels<ReviewSearchHospitalViewModel>()
    private lateinit var adapter: ReviewSearchHospitalAdapter
    private var selectedHospitalName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerViewAdapter()
        observeHospitalList()
        initSearchEditText()
        viewModel.loadHospitalsFromServer()

        binding.tvNext.setOnClickListener {
            if (selectedHospitalName != null) {
                val intent = Intent(this, ReviewWriteActivity::class.java).apply {
                    putExtra("hospital_name", selectedHospitalName)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "병원을 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerViewAdapter() {
        adapter = ReviewSearchHospitalAdapter { hospitalName ->
            selectedHospitalName = hospitalName
        }
        binding.rvReviewSearchHospital.adapter = adapter
        binding.rvReviewSearchHospital.layoutManager = LinearLayoutManager(this)
    }

    private fun observeHospitalList() {
        viewModel.hospitalList.observe(this) { hospitalList ->
            adapter.submitList(hospitalList)
            binding.rvReviewSearchHospital.visibility =
                if (hospitalList.isEmpty()) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun initSearchEditText() {
        with(binding.etHospitalName) {
            imeOptions = EditorInfo.IME_ACTION_DONE
            setSingleLine(true)

            addTextChangedListener { editable ->
                val query = editable.toString()
                viewModel.filterHospitals(query)
            }

            setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                    hideKeyboard()
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etHospitalName.windowToken, 0)
    }
}
