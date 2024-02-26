package com.example.supertest.data

import android.media.Image
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET


class SuperHero {
    @SerializedName("name") var name:String="-"
    @SerializedName("biography") var biography:String ="-"
    @SerializedName("image") var image:Image = TODO()

}

class SuperResponse{


    @SerializedName("respose") val response=:String

            @GET("https://www.superheroapi.com/api.php/7252591128153666/search/super")

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