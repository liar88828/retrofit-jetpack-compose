package com.tutor.retrofit_app.quote.di

import com.tutor.retrofit_app.quote.data.QuoteApi
import com.tutor.retrofit_app.quote.utils.ConstQuote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuoteModule {

	@Provides
	@Singleton
	fun providesLoggingInterceptor(): HttpLoggingInterceptor {
		return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
	}

	@Provides
	@Singleton
	fun providerOkHttpClient(
		loggingInterceptor: HttpLoggingInterceptor
	): OkHttpClient {
		val okHttpClient = OkHttpClient.Builder()
		okHttpClient.callTimeout(40, TimeUnit.SECONDS)
		okHttpClient.connectTimeout(40, TimeUnit.SECONDS)
		okHttpClient.readTimeout(40, TimeUnit.SECONDS)
		okHttpClient.writeTimeout(40, TimeUnit.SECONDS)
		okHttpClient.addInterceptor(loggingInterceptor)
		return okHttpClient.build()
	}

	@Provides
	@Singleton
	fun provideQuoteApi(
		client: OkHttpClient
	): Retrofit {
		return Retrofit.Builder()
			.baseUrl(ConstQuote.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build()
	}

	@Provides
	@Singleton
	fun provideApi(
		retrofit: Retrofit
	): QuoteApi = retrofit.create(QuoteApi::class.java)
}