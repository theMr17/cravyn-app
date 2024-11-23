package com.cravyn.app.util

/**
 * Extension function that converts an HTTP URL to HTTPS if the URL starts with "http://".
 *
 * This function checks if the current string (URL) is not null and begins with "http://".
 * If it does, it replaces the "http" part with "https". Otherwise, the URL remains unchanged.
 *
 * @receiver String? The URL string to be converted.
 * @return String? A new URL with "http" replaced by "https", or the original URL if it already uses HTTPS or doesn't start with "http".
 */
fun String?.toHttpsUrl(): String? {
    return this?.let {
        // If the URL starts with "http://", replace it with "https://"
        if (it.startsWith("http://")) {
            it.replace("http://", "https://")
        } else {
            it  // Return the URL as is if it's already using https or doesn't start with http
        }
    }
}
