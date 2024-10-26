package com.cravyn.app.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.cravyn.app.databinding.OtpEditTextBinding

/**
 * A custom view that provides an OTP (One-Time Password) input interface
 * consisting of multiple EditTexts arranged in a row.
 *
 * This view automatically moves the focus to the next EditText when the user
 * inputs a character and moves back when a character is deleted.
 *
 * @property context The context in which the view is running.
 * @property attrs The attributes for this view, if any.
 * @property defStyleAttr Default style attributes for this view.
 */
class OtpEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: OtpEditTextBinding
    private val editTexts: Array<EditText>

    init {
        // Inflate the layout and bind the views.
        binding = OtpEditTextBinding.inflate(LayoutInflater.from(context), this, true)

        // Initialize the EditTexts array with references from the binding.
        editTexts =
            arrayOf(binding.editText1, binding.editText2, binding.editText3, binding.editText4)

        // Set up text change listeners for each EditText.
        addTextChangeListeners()
    }

    /**
     * Adds text change listeners to each EditText in the array.
     * Listeners handle focus changes based on user input.
     */
    private fun addTextChangeListeners() {
        editTexts.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // No action needed before text changes.
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // No action needed during text changes.
                }

                override fun afterTextChanged(s: Editable?) {
                    // Handle focus and input changes after text has changed.
                    handleTextChange(index, s)
                }
            })
        }
    }

    /**
     * Handles focus movement based on the text input in each EditText.
     *
     * @param index The index of the EditText that changed.
     * @param s The current text in the EditText after the change.
     */
    private fun handleTextChange(index: Int, s: Editable?) {
        val text = s.toString()
        // Move focus to the next EditText if a character is entered.
        if (text.length == 1) {
            if (index < editTexts.size - 1 && editTexts[index + 1].text.isEmpty()) {
                editTexts[index + 1].requestFocus()
            }
            // Move focus back to the previous EditText if it was emptied.
        } else if (text.isEmpty() && index > 0 && editTexts[index - 1].text.isNotEmpty()) {
            editTexts[index - 1].requestFocus()
            editTexts[index - 1].setSelection(editTexts[index - 1].text.length)
        }
    }

    /**
     * Retrieves the concatenated text from all EditTexts.
     *
     * @return A string containing the combined input from all EditTexts.
     */
    fun getText(): String = editTexts.joinToString("") { it.text.toString() }

    /**
     * Clears the text in all EditTexts and sets focus to the first EditText.
     */
    fun clear() {
        editTexts.forEach { it.setText("") }
        editTexts[0].requestFocus()
    }
}
