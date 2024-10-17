package com.cravyn.app.util

import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

/**
 * Utility object for managing the visibility of a loading bar in place of a button.
 *
 * This utility is used to show or hide a loading bar while performing background operations,
 * temporarily replacing the button with a loading indicator to enhance user experience.
 */
object LoadingBarUtil {

    /**
     * Toggles the visibility of a button and a loading bar based on the loading state.
     *
     * If [isLoading] is `true`, the button is hidden and the loading bar container is shown.
     * If [isLoading] is `false`, the button is shown and the loading bar container is hidden.
     *
     * @param isLoading Boolean flag indicating whether to show the loading bar.
     * @param button The [MaterialButton] to toggle visibility.
     * @param loadingBarContainer The [MaterialCardView] containing the loading bar to toggle visibility.
     */
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
