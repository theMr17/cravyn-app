package com.cravyn.app.features.auth.forgotpassword

/** Represents current state of [ForgotPasswordActivity]. */
enum class ForgotPasswordPages(val value: Int) {
    ENTER_EMAIL(0),
    OTP_VERIFICATION(1),
    NEW_PASSWORD(2)
}
