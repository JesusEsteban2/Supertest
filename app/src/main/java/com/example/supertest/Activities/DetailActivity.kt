package com.example.supertest.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.supertest.R
import com.example.supertest.data.RetrofitBuilder
import com.example.supertest.data.RetrofitService
import com.example.supertest.data.SuperHero
import com.example.supertest.data.SuperHeroResponse
import com.example.supertest.databinding.ActivityDetailBinding
import com.example.supertest.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras=intent.extras

        val id=extras?.getString("EXTRA_ID")?:"Sin Id"
        val name=extras?.getString("EXTRA_NAME")?:"Sin Nombre"
        val urlImage=extras?.getString("EXTRA_IMAGE")?:"https://i.imgur.com/DvpvklR.png"

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Llamada a api retrofit.
        val service: RetrofitService = RetrofitBuilder.getService()

        // Llamada en 2º plano
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.searchById(id)

            runOnUiThread {
                // Modificar UI
                //binding.progress.visibility = View.GONE

                if (response.body() != null && response.isSuccessful == true) {
                    Log.i("HTTP", "respuesta correcta :)")
                    Log.i ("HTTP", response.body().toString())
                    render(response.body()!!)
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                }
            }
        }

    }

    fun render(superhero: SuperHero){
        binding.nameSuper.text=superhero.name
        Picasso.get().load(superhero.httpImage.url).into(binding.imageSuper);
        binding.fullName.text=superhero.biography.fullName
        binding.alignment.text=superhero.biography.alignment
        binding.alterEgos.text=superhero.biography.alterEgos
        binding.firstAppearance.text=superhero.biography.firstAppearance
    }

}