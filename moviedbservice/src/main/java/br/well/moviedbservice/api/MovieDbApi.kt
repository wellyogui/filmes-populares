package br.well.moviedbservice.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object MovieDbApi {

    private val gson: Gson by lazy {
        val gsonBuilder = gsonBuilder(FieldNamingPolicy.IDENTITY)
        registerTypeAdapter(gsonBuilder)
            .create()
    }

    lateinit var retrofit: Retrofit

    fun buildRetrofit(): Retrofit {

        val baseUrl = "https://api.themoviedb.org/3/"
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val url = original.url().newBuilder().build()
                val requestBuilder = original.newBuilder().url(url)
                requestBuilder.header("Content-Type", "application/json")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder().client(okClient).baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()

        return retrofit
    }

    private fun gsonBuilder(fieldNamePolicy: FieldNamingPolicy): GsonBuilder {
        return GsonBuilder()
            .setFieldNamingPolicy(fieldNamePolicy)
            .setPrettyPrinting()
    }

    private fun registerTypeAdapter(gsonBuilder: GsonBuilder): GsonBuilder {
        return gsonBuilder
    }
}