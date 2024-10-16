package com.cravyn.app.util

import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

object LoadingBarUtil {
    fun showButtonLoadingBar(
        isLoading: Boolean,
        button: MaterialButton,
        loadingBarContainer: MaterialCardView
    ) {
        if (isLoading) {
            button.visibility = View.INVISIBLE
            loadingBarContainer.visibility = View.VISIBLE
        } else {
            button.visibility = View.VISIBLE
            loadingBarContainer.visibility = View.INVISIBLE
        }
    }
}
