package com.projetointegrador.grupo04.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url().newBuilder()
            .addQueryParameter(API_KEY, PUBLIC_KEY)
            .addQueryParameter(API_LANG, LANGUAGE_REQUEST)
            .build()

        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY = "api_key"
        private const val API_LANG = "language"

        const val LANGUAGE_REQUEST = "pt-BR"
        const val PUBLIC_KEY = "f9218c6e4d89c1979a30e457170ca934"
    }
}