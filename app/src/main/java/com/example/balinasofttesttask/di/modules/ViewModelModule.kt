package com.example.balinasofttesttask.di.modules

import androidx.lifecycle.ViewModel
import com.example.balinasofttesttask.di.viewModel.ViewModelKey
import com.example.balinasofttesttask.ui.auth.AuthViewModel
import com.example.balinasofttesttask.ui.base_view_model.TokenViewModel
import com.example.balinasofttesttask.ui.camera.CameraViewModel
import com.example.balinasofttesttask.ui.comments.CommentsViewModel
import com.example.balinasofttesttask.ui.map.MapViewModel
import com.example.balinasofttesttask.ui.photos.PhotoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PhotoViewModel::class)
    fun bindPhotoViewModel(viewModel: PhotoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    fun bindMapViewModel(viewModel: MapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TokenViewModel::class)
    fun bindTokenViewModel(viewModel: TokenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CameraViewModel::class)
    fun bindCameraViewModel(viewModel: CameraViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    fun bindCommentViewModel(viewModel: CommentsViewModel): ViewModel
}
