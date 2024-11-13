package com.example.trabalho1ddm.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.trabalho1ddm.ui.fragment.LoginFragment
import com.example.trabalho1ddm.R
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            loadFragment(LoginFragment())
        }
    }

    fun onLoginSuccess() {
        goToHome()
    }

    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish() // Finaliza a MainActivity para impedir retorno ao login
    }
}
