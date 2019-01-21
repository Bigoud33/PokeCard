package com.example.lpiem.pokecard.presentation.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.presentation.ui.fragments.RegisterFragment
import dagger.android.AndroidInjection

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, RegisterFragment())
                .commit()
        }
    }
}