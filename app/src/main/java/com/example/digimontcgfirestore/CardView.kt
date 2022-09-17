package com.example.digimontcgfirestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

enum class ProviderType {
    BASIC
}

class CardView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)
    }
}