package com.example.lpiem.pokecard.di

import android.app.Application
import com.example.lpiem.pokecard.PokeCardApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, RetrofitModule::class, ActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(pokeCardApp: PokeCardApplication)
}