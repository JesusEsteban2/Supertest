package com.example.supertest.Activities
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supertest.R
import com.example.supertest.adapters.SuperheroAdapter
import com.example.supertest.data.SuperHero
import com.example.supertest.data.RetrofitService
import com.example.supertest.data.SuperHeroResponse
import com.example.supertest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: SuperheroAdapter
    private var superheroList:List<SuperHero> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = SuperheroAdapter() {
            onItemClickListener(it)
        }
        binding.recViewMain.adapter = adapter
        binding.recViewMain.layoutManager = GridLayoutManager(this, 2)
        binding.findButton.setOnClickListener({searchSuperheroes(binding.editSearch.text.toString())})
    }

    private fun onItemClickListener(position:Int) {
        val superhero: SuperHero = superheroList[position]

        val intent = Intent(this, DetailActivity::class.java)
        //intent.putExtra(DetailActivity.EXTRA_ID, superhero.id)
        //intent.putExtra(DetailActivity.EXTRA_NAME, superhero.name)
        //intent.putExtra(DetailActivity.EXTRA_IMAGE, superhero.httpImage.url)
        startActivity(intent)
        //Toast.makeText(this, getString(horoscope.name), Toast.LENGTH_LONG).show()
    }

    private fun searchSuperheroes(query: String) {
        //binding.progress.visibility = View.VISIBLE

         var retrofit = Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/api.php/7252591128153666/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: RetrofitService = retrofit.create(RetrofitService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val response = service.searchByName(query)

            runOnUiThread {
                // Modificar UI
                //binding.progress.visibility = View.GONE

                if (response.body() != null) {
                    Log.i("HTTP", "respuesta correcta :)")
                    superheroList = response.body()!!.listSuperHero
                    adapter.updateItems(superheroList)

                    if (superheroList.isNotEmpty()) {
                        binding.recViewMain.visibility = View.VISIBLE
                        //binding.emptyPlaceholder.visibility = View.GONE
                    } else {
                        binding.recViewMain.visibility = View.GONE
                        //binding.emptyPlaceholder.visibility = View.VISIBLE
                    }
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                }
            }
        }
    }
}
