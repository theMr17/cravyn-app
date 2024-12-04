package com.cravyn.app.di

import android.content.Context
import com.cravyn.app.BuildConfig
import com.cravyn.app.data.api.AuthInterceptor
import com.cravyn.app.data.api.TokenAuthenticator
import com.cravyn.app.features.address.AddressApi
import com.cravyn.app.features.address.AddressRepository
import com.cravyn.app.features.address.AddressRepositoryImpl
import com.cravyn.app.features.auth.AuthApi
import com.cravyn.app.features.auth.AuthRepository
import com.cravyn.app.features.auth.AuthRepositoryImpl
import com.cravyn.app.features.auth.JwtTokenRepository
import com.cravyn.app.features.auth.JwtTokenRepositoryImpl
import com.cravyn.app.features.cart.CartApi
import com.cravyn.app.features.cart.CartRepository
import com.cravyn.app.features.cart.CartRepositoryImpl
import com.cravyn.app.features.home.HomeApi
import com.cravyn.app.features.home.HomeRepository
import com.cravyn.app.features.home.HomeRepositoryImpl
import com.cravyn.app.features.profile.ProfileApi
import com.cravyn.app.features.profile.ProfileRepository
import com.cravyn.app.features.profile.ProfileRepositoryImpl
import com.cravyn.app.features.query.QueryApi
import com.cravyn.app.features.query.QueryRepository
import com.cravyn.app.features.query.QueryRepositoryImpl
import com.cravyn.app.features.restaurant.RestaurantApi
import com.cravyn.app.features.restaurant.RestaurantRepository
import com.cravyn.app.features.restaurant.RestaurantRepositoryImpl
import com.cravyn.app.features.search.SearchApi
import com.cravyn.app.features.search.SearchRepository
import com.cravyn.app.features.search.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context,
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB cache.
        val myCache = Cache(context.cacheDir, cacheSize)

        val builder = OkHttpClient().newBuilder()

        builder.cache(myCache)
            .addInterceptor(authInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .authenticator(tokenAuthenticator)

        // Add logging interceptor in debug mode.
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun providesJwtTokenRepository(impl: JwtTokenRepositoryImpl): JwtTokenRepository = impl


    @Provides
    fun providesHomeRepository(impl: HomeRepositoryImpl): HomeRepository = impl

    @Provides
    fun providesRestaurantRepository(impl: RestaurantRepositoryImpl): RestaurantRepository = impl

    @Provides
    fun providesSearchRepository(impl: SearchRepositoryImpl): SearchRepository = impl

    @Provides
    fun providesCartRepository(impl: CartRepositoryImpl): CartRepository = impl

    @Provides
    fun providesAddressRepository(impl: AddressRepositoryImpl): AddressRepository = impl

    @Provides
    fun providesQueryRepository(impl: QueryRepositoryImpl): QueryRepository = impl

    @Provides
    fun providesProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository = impl

    @Provides
    @Singleton
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providesHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRestaurantApi(retrofit: Retrofit): RestaurantApi {
        return retrofit.create(RestaurantApi::class.java)
    }

    @Provides
    @Singleton
    fun providesSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCartApi(retrofit: Retrofit): CartApi {
        return retrofit.create(CartApi::class.java)
    }

    @Provides
    @Singleton
    fun providesAddressApi(retrofit: Retrofit): AddressApi {
        return retrofit.create(AddressApi::class.java)
    }

    @Provides
    @Singleton
    fun providesQueryApi(retrofit: Retrofit): QueryApi {
        return retrofit.create(QueryApi::class.java)
    }

    @Provides
    @Singleton
    fun providesProfileApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }
}
