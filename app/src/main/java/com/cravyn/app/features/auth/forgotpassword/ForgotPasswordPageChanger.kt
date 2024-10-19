package com.cravyn.app.features.auth.forgotpassword

/** Listener for when an activity should change pages during the forgot password flow. */
interface ForgotPasswordPageChanger {
    /** Navigates user to the next page in the forgot password flow. */
    fun changePage(pageNumber: Int)
}
