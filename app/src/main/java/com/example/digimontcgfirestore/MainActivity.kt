package com.example.digimontcgfirestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.digimontcgfirestore.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_main)

        setup()
    }

    private fun setup(){

        binding.RegisterButton.setOnClickListener {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.EmailText.text.toString(), binding.PassText.text.toString()).addOnCompleteListener(){
                if (it.isSuccessful){
                    it.result.user?.email?.let { it1 -> showHome(it1, ProviderType.BASIC) }
                }
                else {
                    showAlert()
                }
            }
        }



    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("There was an error creating your user")
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, CardView::class.java).apply{
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
    }
}



