package com.example.digimontcgfirestore.adapter

import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Restaurant POJO.
 */
@IgnoreExtraProperties
data class Cards(
    var name: String? = null,
    var color: String? = null,
    var power: Int = 0,
    var photo: String? = null,
    var level: Int = 0
) {

    companion object {

        const val FIELD_NAME = "name"
        const val FIELD_COLOR = "color"
        const val FIELD_POWER = "power"
        const val FIELD_LEVEL = "level"
    }
}
