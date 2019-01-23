package com.example.lpiem.pokecard.di

import com.example.lpiem.pokecard.presentation.ui.activities.LoginActivity
import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import com.example.lpiem.pokecard.presentation.ui.activities.RegisterActivity
import com.example.lpiem.pokecard.presentation.ui.fragments.LoginFragment
import com.example.lpiem.pokecard.presentation.ui.fragments.PokedexFragment
import com.example.lpiem.pokecard.presentation.ui.fragments.PokeshopFragment
import com.example.lpiem.pokecard.presentation.ui.fragments.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun contributeRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector
    internal abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    internal abstract fun contributePokedexFragment(): PokedexFragment

    @ContributesAndroidInjector
    internal abstract fun contributePokeshopFragment(): PokeshopFragment
}