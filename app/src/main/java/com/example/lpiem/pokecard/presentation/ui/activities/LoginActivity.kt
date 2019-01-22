package com.example.lpiem.pokecard.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.presentation.ui.fragments.LoginFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class LoginActivity: AppCompatActivity(), HasSupportFragmentInjector{

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = injector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, LoginFragment())
                .commit()
        }
    }
}