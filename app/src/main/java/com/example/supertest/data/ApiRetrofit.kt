package com.example.supertest.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
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
        var retrofit = Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/api.php/7252591128153666/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: RetrofitService = retrofit.create(RetrofitService::class.java)

        return service
    }
}





/*
object RetrofitAdapter {
    var httpClient = OkHttpClient.Builder()
    var retrofit = Retrofit.Builder()
        .baseUrl("https://www.superheroapi.com/api.php/7252591128153666/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
}




// creates okHttp engine with logger interceptor
val okHttp = OkHttpClient.Builder()
    .build()

val retrofit = Retrofit.Builder()
// add base url for all request
    .baseUrl("https://www.superheroapi.com/api.php/7252591128153666/search/")
// add gson content negotiation
    .addConverterFactory(GsonConverterFactory.create())
// add adapter to wrap response with result
    .addCallAdapterFactory(ResultCallAdapterFactory.create())
    .client(okHttp)
    .build()

fun searchSuperhero(query:String) {


    runOnUiTherad {
        if (response!=null) {
            Log.i("HTTP","Respuesta Correcta :)")
            Log.i("HTTP","Respuesta: ${respose.body()?.response}")
            for (superhero in response.body()?.results!!){

            }

        }
    }


}*/