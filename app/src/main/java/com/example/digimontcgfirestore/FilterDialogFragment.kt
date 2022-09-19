package com.example.digimontcgfirestore

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.digimontcgfirestore.databinding.DialogFiltersBinding
import com.example.digimontcgfirestore.R
import com.example.digimontcgfirestore.adapter.Cards
import com.google.firebase.firestore.Query

/**
 * Dialog Fragment containing filter form.
 */
class FilterDialogFragment : DialogFragment() {

    private var _binding: DialogFiltersBinding? = null
    private val binding get() = _binding!!
    private var filterListener: FilterListener? = null

    private val selectedCategory: Int
        get() {
            val selected = binding.spinnerCity.selectedItem as String
            return when (selected) {
                getString(R.string.power_0) -> 0
                getString(R.string.power_1000) -> 1000
                getString(R.string.power_2000) -> 2000
                getString(R.string.power_3000) -> 3000
                getString(R.string.power_4000) -> 4000
                getString(R.string.power_5000) -> 5000
                getString(R.string.power_6000) -> 6000
                getString(R.string.power_7000) -> 7000
                getString(R.string.power_8000) -> 8000
                getString(R.string.power_9000) -> 9000
                getString(R.string.power_10000) -> 10000
                getString(R.string.power_11000) -> 11000
                getString(R.string.power_12000) -> 12000
                else -> -1
            }
        }

    private val selectedCity: String?
        get() {
            val selected = binding.spinnerCategory.selectedItem as String
            return if (getString(R.string.value_any_cards) == selected) {
                null
            } else {
                selected
            }
        }

    private val selectedPrice: Int
        get() {
            val selected = binding.spinnerPrice.selectedItem as String
            return when (selected) {
                getString(R.string.level_2) -> 2
                getString(R.string.level_3) -> 3
                getString(R.string.level_4) -> 4
                getString(R.string.level_5) -> 5
                getString(R.string.level_6) -> 6
                else -> -1
            }
        }

    private val selectedOwner: String?
        get() {
            val selected = binding.spinnerOwner.selectedItem as String
            return if (getString(R.string.value_any_owned) == selected) {
                null
            } else {
                selected
            }
        }

    private val selectedSortBy: String?
        get() {
            val selected = binding.spinnerSort.selectedItem as String
            if (getString(R.string.sort_by_card) == selected) {
                return Cards.FIELD_COLOR
            }
            if (getString(R.string.sort_by_level) == selected) {
                return Cards.FIELD_LEVEL
            }
            return if (getString(R.string.sort_by_power) == selected) {
                Cards.FIELD_POWER
            } else {
                null
            }
        }

    private val sortDirection: Query.Direction
        get() {
            val selected = binding.spinnerSort.selectedItem as String
            if (getString(R.string.sort_by_card) == selected) {
                return Query.Direction.DESCENDING
            }
            if (getString(R.string.sort_by_level) == selected) {
                return Query.Direction.ASCENDING
            }
            return if (getString(R.string.sort_by_power) == selected) {
                Query.Direction.DESCENDING
            } else {
                Query.Direction.DESCENDING
            }
        }

    val filters: Filters
        get() {
            val filters = Filters()

            filters.power = selectedCategory
            filters.level = selectedPrice
            filters.color = selectedCity
            filters.sortBy = selectedSortBy

            return filters
        }

    interface FilterListener {

        fun onFilter(filters: Filters)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFiltersBinding.inflate(inflater, container, false)

        binding.buttonSearch.setOnClickListener { onSearchClicked() }
        binding.buttonCancel.setOnClickListener { onCancelClicked() }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (parentFragment is FilterListener) {
            filterListener = parentFragment as FilterListener
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun onSearchClicked() {
        filterListener?.onFilter(filters)
        dismiss()
    }

    private fun onCancelClicked() {
        dismiss()
    }

    fun resetFilters() {
        _binding?.let {
            it.spinnerCategory.setSelection(0)
            it.spinnerCity.setSelection(0)
            it.spinnerPrice.setSelection(0)
            it.spinnerSort.setSelection(0)
        }
    }

    companion object {

        const val TAG = "FilterDialog"
    }
}
