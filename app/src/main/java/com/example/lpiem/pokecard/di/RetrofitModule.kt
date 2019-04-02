package com.example.lpiem.pokecard.di

import android.content.Context
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.common.Constants
import com.example.lpiem.pokecard.data.network.PokeCardService
import com.github.ajalt.timberkt.Timber
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.AccessControlContext
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

@Module
internal class RetrofitModule {

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .create()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Timber.tag("network").v(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor, context: Context): OkHttpClient {

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
            .addNetworkInterceptor(loggingInterceptor)
            .sslSocketFactory(sslSocketFactory, x509TrustManager)
            .hostnameVerifier(myHostNameVerifier())

        return clientBuilder.build()
    }

    private fun myHostNameVerifier(): HostnameVerifier {
        return HostnameVerifier { hostname, _ ->
            if (hostname == "bigoud.games") {
                return@HostnameVerifier true
            }

            false
        }
    }


    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    @Provides
    fun provideService(retrofit: Retrofit): PokeCardService =
        retrofit.create(PokeCardService::class.java)
}