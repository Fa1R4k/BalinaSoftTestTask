package com.example.balinasofttesttask

import android.app.Application
import com.example.balinasofttesttask.di.ApplicationComponent
import com.example.balinasofttesttask.di.DaggerApplicationComponent


class DaggerApp : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}
