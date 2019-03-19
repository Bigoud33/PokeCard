package com.example.lpiem.pokecard.di

import android.content.Context
import dagger.Module
import dagger.Provides
import java.security.AccessControlContext

@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideContext() = context
}