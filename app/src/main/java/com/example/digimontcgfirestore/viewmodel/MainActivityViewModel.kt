package com.example.digimontcgfirestore.viewmodel

import androidx.lifecycle.ViewModel
import com.example.digimontcgfirestore.Filters

/**
 * ViewModel for [com.google.firebase.example.fireeats.MainActivity].
 */

class MainActivityViewModel : ViewModel() {

    var isSigningIn: Boolean = false
    var filters: Filters = Filters.default
}
