package com.cravyn.app.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.cravyn.app.databinding.OtpEditTextBinding

class OtpEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: OtpEditTextBinding
    private val editTexts: Array<EditText>

    init {
        binding = OtpEditTextBinding.inflate(LayoutInflater.from(context), this, true)
        editTexts =
            arrayOf(binding.editText1, binding.editText2, binding.editText3, binding.editText4)
        addTextChangeListeners()
    }

    private fun addTextChangeListeners() {
        editTexts.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    handleTextChange(index, s)
                }
            })
        }
    }

    private fun handleTextChange(index: Int, s: Editable?) {
        val text = s.toString()
        if (text.length == 1) {
            if (index < editTexts.size - 1 && editTexts[index + 1].text.isEmpty()) {
                editTexts[index + 1].requestFocus()
            }
        } else if (text.isEmpty() && index > 0 && editTexts[index - 1].text.isNotEmpty()) {
            editTexts[index - 1].requestFocus()
            editTexts[index - 1].setSelection(editTexts[index - 1].text.length)
        }
    }

    fun getText(): String = editTexts.joinToString("") { it.text.toString() }

    fun clear() {
        editTexts.forEach { it.setText("") }
        editTexts[0].requestFocus()
    }
}
