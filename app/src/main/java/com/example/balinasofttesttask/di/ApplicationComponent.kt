package com.example.balinasofttesttask.di

import android.content.Context
import com.example.balinasofttesttask.MainActivity
import com.example.balinasofttesttask.di.modules.DataBaseModule
import com.example.balinasofttesttask.di.modules.DataModule
import com.example.balinasofttesttask.di.modules.NetworkModule
import com.example.balinasofttesttask.di.modules.SourceModule
import com.example.balinasofttesttask.di.modules.ViewModelModule
import com.example.balinasofttesttask.ui.auth.AuthActivity
import com.example.balinasofttesttask.ui.auth.login.LoginFragment
import com.example.balinasofttesttask.ui.auth.registration.RegistrationFragment
import com.example.balinasofttesttask.ui.camera.CameraActivity
import com.example.balinasofttesttask.ui.comments.CommentsFragment
import com.example.balinasofttesttask.ui.map.MapFragment
import com.example.balinasofttesttask.ui.photos.PhotoFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class, SourceModule::class, DataModule::class, DataBaseModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: AuthActivity)
    fun inject(activity: CameraActivity)
    fun inject(fragment: MapFragment)
    fun inject(fragment: PhotoFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: CommentsFragment)
}