package com.example.supertest.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitService {

    @GET("search/{query}")
    suspend fun searchByName(@Path("query") query: String?): Response<SuperHeroResponse>

    @GET("{id}")
    suspend fun searchById(@Path("id") id:String?):Response<SuperHero>

}

object RetrofitBuilder {

    fun getService():RetrofitService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/api.php/7252591128153666/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: RetrofitService = retrofit.create(RetrofitService::class.java)

        return service
    }
}
