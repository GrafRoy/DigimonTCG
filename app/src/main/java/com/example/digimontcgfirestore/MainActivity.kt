package com.example.digimontcgfirestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.digimontcgfirestore.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(findViewById(id.toolbar))
        Navigation.findNavController(this, id.nav_host_fragment)
            .setGraph(navigation.nav_graph_kotlin)
    }
}