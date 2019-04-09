package com.example.lpiem.pokecard.di

import com.example.lpiem.pokecard.presentation.ui.activities.LoginActivity
import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import com.example.lpiem.pokecard.presentation.ui.activities.RegisterActivity
import com.example.lpiem.pokecard.presentation.ui.fragments.*
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

    @ContributesAndroidInjector
    internal abstract fun contributePokeshopBuyFragment(): PokeshopBuyFragment

    @ContributesAndroidInjector
    internal abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    internal abstract fun contributePokemonDetailsFragment(): PokemonDetailsFragment

    @ContributesAndroidInjector
    internal abstract fun  contributePokemonExchangeFragment(): ExchangeFragment

    @ContributesAndroidInjector
    internal abstract fun  contributePokemonExchangeFirstRespondFragment(): ExchangeFirstRespondFragment

}