package com.example.dec16_movie_started.network

import com.example.dec16_movie_started.data.ResultResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

// base URL for the web service.
private const val BASE_URL =
    "https://api.themoviedb.org/3/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MoviesAPIService {

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmOTBkODU1MzZkOGI2MDUwNjMzYTU5YzFiMWI2MTM4ZiIsInN1YiI6IjYxYjhmOTU2ZTI2M2JiMDAxYzRiOWEyNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hcyGZm_ieY0K7N8z8VyQZJ2KxOjB1rh57NhQBSkRpJQ",
        "Content-Type: application/json;charset=utf-8"
    )
    //coroutine added
    @GET("movie/top_rated")
    suspend fun getMostPopular(): ResultResponse

}
object MoviesApi {
    val retrofitService : MoviesAPIService by lazy {
        retrofit.create(MoviesAPIService::class.java) }
}


/*

An Interceptor allows you to intercept and modify HTTP requests before they are sent to the server. For headers that need to be dynamically added (e.g., Authorization tokens), this is a cleaner solution than hardcoding static headers.
val authInterceptor = Interceptor { chain ->
    val originalRequest: Request = chain.request()
    val modifiedRequest = originalRequest.newBuilder()
        .addHeader("Authorization", "Bearer YOUR_DYNAMIC_TOKEN") // Add dynamic Authorization header
        .addHeader("Content-Type", "application/json;charset=utf-8") // Content-Type header
        .build()
    chain.proceed(modifiedRequest)
}

// OkHttpClient with the interceptor
val client = OkHttpClient.Builder()
    .addInterceptor(authInterceptor)
    .build()

-----------
val retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/") // Example base URL
    .addConverterFactory(MoshiConverterFactory.create()) // Use Moshi for JSON parsing
    .client(client) // Add the custom OkHttpClient with interceptor
    .build()

*/