package com.example.lpiem.pokecard

import android.app.Activity
import android.app.Application
import com.example.lpiem.pokecard.di.AppModule
import com.example.lpiem.pokecard.di.DaggerAppComponent
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.github.ajalt.timberkt.Timber
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import com.squareup.picasso.Picasso



class PokeCardApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
        initFacebook()
        initPicasso()

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    fun initDagger() {
        DaggerAppComponent
            .builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }

    fun initTimber() {
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())
    }

    fun initFacebook() {
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }

    fun initPicasso() {
        val picassoBuilder = Picasso.Builder(this)
        val picasso = picassoBuilder.build()
        try {
            Picasso.setSingletonInstance(picasso)
        } catch (ignored: IllegalStateException) {
            // Picasso instance was already set
            // cannot set it after Picasso.with(Context) was already in use
        }

    }
}