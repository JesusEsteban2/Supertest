package com.example.supertest.data

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET


class SuperHero {


    @SerializedName("respose") val response=:String

            @GET("api/7252591128153666""https://superheroapi.com/")

    fun searchSuperhero(query:String) {
        val retrofit=RetrofitLib.





    //llamada en 2º plano
    val response=service.serachByName()

    runOnUiTherad {
        if (response!=null) {
            Log.i("HTTP","Respuesta Correcta :)")
            Log.i("HTTP","Respuesta: ${respose.body()?.response}")
            for (superhero in response.body()?.results!!){

            }

        }
    }


    }
}

Class SuperheroResponse {
    @SerializedName()
}