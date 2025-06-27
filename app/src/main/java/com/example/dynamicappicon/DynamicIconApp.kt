package com.example.dynamicappicon

import android.app.Application
import com.google.firebase.FirebaseApp

class DynamicIconApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}