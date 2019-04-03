package com.example.lpiem.pokecard

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.lpiem.pokecard.di.AppModule
import com.example.lpiem.pokecard.di.DaggerAppComponent
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.github.ajalt.timberkt.Timber
import com.parse.ParseInstallation
import com.squareup.picasso.OkHttp3Downloader
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import com.squareup.picasso.Picasso
import com.parse.Parse
import okhttp3.OkHttpClient
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


class PokeCardApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
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
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("15781654923854924")
                // if defined
                //.clientKey("YOUR_CLIENT_KEY")
                .server("https://bigoud.games/pokecard/")
                .clientBuilder(provideHttpClient(this))
                .build()
        )
        ParseInstallation.getCurrentInstallation().saveInBackground()
    }

    fun initFacebook() {
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }

    fun provideHttpClient(context: Context): OkHttpClient.Builder {

        // Load CAs from an InputStream
        val certificateFactory = CertificateFactory.getInstance("X.509")

        // Load self-signed certificate (*.crt file)
        val inputStream =  context.resources.openRawResource(R.raw.bigoudinc)
        val certificate = certificateFactory.generateCertificate(inputStream)
        inputStream.close()

        // Create a KeyStore containing our trusted CAs
        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        keyStore.setCertificateEntry("ca", certificate)

        // Create a TrustManager that trusts the CAs in our KeyStore.
        val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm)
        trustManagerFactory.init(keyStore)

        val trustManagers = trustManagerFactory.trustManagers
        val x509TrustManager = trustManagers[0] as X509TrustManager

        // Create an SSLSocketFactory that uses our TrustManager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(x509TrustManager), null)
        var sslSocketFactory = sslContext.socketFactory



        val clientBuilder = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .sslSocketFactory(sslSocketFactory, x509TrustManager)
            .hostnameVerifier(myHostNameVerifier())

        return clientBuilder
    }

    private fun myHostNameVerifier(): HostnameVerifier {
        return HostnameVerifier { hostname, _ ->
            if (hostname == "bigoud.games") {
                return@HostnameVerifier true
            }

            false
        }
    }

    fun initPicasso() {
        val picassoBuilder = Picasso.Builder(this)
        picassoBuilder.downloader(OkHttp3Downloader(provideHttpClient(this).build()))
        val picasso = picassoBuilder.build()
        try {
            Picasso.setSingletonInstance(picasso)
        } catch (ignored: IllegalStateException) {
            // Picasso instance was already set
            // cannot set it after Picasso.with(Context) was already in use
        }

    }
}