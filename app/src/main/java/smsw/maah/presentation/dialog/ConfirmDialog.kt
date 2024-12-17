package smsw.maah.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import smsw.maah.databinding.DialogConfirmBinding

class ConfirmDialog(
    context: Context,
    private val message: String,
    private val subMessage: String,
    private val confirmButtonText: String = "확인", // 기본값 설정
    private val cancelButtonText: String = "취소", // 기본값 설정
    private val onConfirm: () -> Unit,
    private val onCancel: () -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 메시지 설정
        binding.dialogMessage.text = message
        binding.dialogSubmessage.text = subMessage

        // 버튼 텍스트 설정
        binding.confirmButton.text = confirmButtonText
        binding.dismissButton.text = cancelButtonText

        // 버튼 클릭 이벤트
        binding.confirmButton.setOnClickListener {
            onConfirm()
            dismiss()
        }

        binding.dismissButton.setOnClickListener {
            onCancel()
            dismiss()
        }
    }
}