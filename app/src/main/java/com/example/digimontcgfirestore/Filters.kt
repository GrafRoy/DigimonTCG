package com.example.digimontcgfirestore

import android.content.Context
import android.text.TextUtils
import com.example.digimontcgfirestore.adapter.Cards
import com.google.firebase.firestore.Query

/**
 * Object for passing filters around.
 */
class Filters {

    var color: String? = null
    var power: Int = 0
    var level: Int = 0
    var sortBy: String? = null
    var owned: String? = null
    var sortDirection: Query.Direction = Query.Direction.DESCENDING

    fun hasCategory(): Boolean {
        return !TextUtils.isEmpty(color)
    }

    fun hasCity(): Boolean {
        return level > 0
    }

    fun hasPrice(): Boolean {
        return power > 0
    }

    fun hasSortBy(): Boolean {
        return !TextUtils.isEmpty(sortBy)
    }

    fun hasOwned(): Boolean {
        return !TextUtils.isEmpty(owned)
    }

    fun getSearchDescription(context: Context): String {
        val desc = StringBuilder()

        if (power == 0 && level == 0) {
            desc.append("<b>")
            desc.append(context.getString(R.string.all_cards))
            desc.append("</b>")
        }

        if (power > 0 ) {
            desc.append("<b>")
            desc.append(power)
            desc.append("</b>")
        }

        if (power > 0 && color != null) {
            desc.append(" with ")
        }

        if (color != null) {
            desc.append("<b>")
            desc.append(color)
            desc.append("</b>")
        }

        if (level > 0) {
            desc.append(" for ")
            desc.append("<b>")
            desc.append(level)
            desc.append("</b>")
        }

        if (owned != null) {
            desc.append("<b>")
            desc.append(owned)
            desc.append("</b>")
        }

        return desc.toString()
    }

    fun getOrderDescription(context: Context): String {
        return when (sortBy) {
            Cards.FIELD_LEVEL -> context.getString(R.string.sorted_by_level)
            Cards.FIELD_POWER -> context.getString(R.string.sorted_by_power)
            else -> context.getString(R.string.sorted_by_card)
        }
    }

    companion object {

        val default: Filters
            get() {
                val filters = Filters()
                filters.sortBy = Cards.FIELD_COLOR
                filters.sortDirection = Query.Direction.DESCENDING

                return filters
            }
    }
}
