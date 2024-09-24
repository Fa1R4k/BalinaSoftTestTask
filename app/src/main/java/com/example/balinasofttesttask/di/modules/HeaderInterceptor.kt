package com.example.balinasofttesttask.di.modules

import com.example.balinasofttesttask.data.source.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val prefs: TokenDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking {
            prefs.getAccessToken()
        }
        val request = chain.request().newBuilder()
        if (accessToken.isNotEmpty()) {
            request.addHeader("Access-Token", accessToken)
        }
        return chain.proceed(request.build())
    }
}