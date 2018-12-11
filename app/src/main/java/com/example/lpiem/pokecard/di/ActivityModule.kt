package com.example.lpiem.pokecard.di

import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import com.example.lpiem.pokecard.presentation.ui.fragments.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): MainFragment
}