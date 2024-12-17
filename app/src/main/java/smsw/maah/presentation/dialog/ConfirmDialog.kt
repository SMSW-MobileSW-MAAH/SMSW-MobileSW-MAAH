package smsw.maah.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.WindowManager
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

        // 다이얼로그 크기 설정
        dialogResize( 0.8f, 300)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


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

    private fun dialogResize(widthRatio: Float, minHeightDp: Int) {
        val window = this.window

        val metrics = context.resources.displayMetrics
        val width = (metrics.widthPixels * widthRatio).toInt()
        val minHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, minHeightDp.toFloat(), metrics
        ).toInt()

        window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
        window?.attributes?.height = minHeight
    }
}