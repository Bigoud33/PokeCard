package com.example.lpiem.pokecard.di

import android.app.Application
import com.example.lpiem.pokecard.PokeCardApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, RetrofitModule::class, ActivityModule::class, AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }

    fun inject(pokeCardApp: PokeCardApplication)
}