package com.example.gitusers.network

import com.example.gitusers.Model.GitUserDataModeListlItem
import com.example.gitusers.model.GitUserDetailsModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApiService {
    @GET("users")
    suspend fun getGitUsers(): Response<List<GitUserDataModeListlItem>>

    @GET("users/{USERNAME}")
    suspend fun getGitUserDetails(@Path("USERNAME") login: String): Response<GitUserDetailsModel>

    @GET("users/{USERNAME}/followers")
    suspend fun getGitUsersFollowers(@Path("USERNAME") login: String):Response<List<GitUserDataModeListlItem>>

    companion object {
        var gitApiService: GitApiService? = null

        fun getInstance(): GitApiService {
            if (gitApiService == null) {
                gitApiService = Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(GitApiService::class.java)
            }
            return gitApiService!!
        }
    }

}