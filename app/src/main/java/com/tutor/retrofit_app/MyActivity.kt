package com.tutor.retrofit_app

import android.app.Application
import com.tutor.retrofit_app.quote.di.Graph
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyActivity : Application() {
	override fun onCreate() {
		Graph.provide(this)
		super.onCreate()
	}
}