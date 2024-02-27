package com.example.supertest.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supertest.adapters.SuperheroAdapter
import com.example.supertest.data.RetrofitService
import com.example.supertest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    lateinit var recyclerView: RecyclerView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            //setContentView(R.layout.activity_main)
            //session = SessionManager(this)
            initView()
        }

        override fun onResume() {
            super.onResume()
            loadData()
        }

        private fun initView() {
            recyclerView = binding.recViewMain
        }

        private fun loadData() {
            val superHeroAdapter = SuperheroAdapter(listSuperHero) {
                onItemClickListener(it)
            }

            //recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            recyclerView.adapter = superHeroAdapter
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.findButton.setOnClickListener {
            searchByName(binding.editSearch.text.toString())
        }
    }
        recyclerView=binding.recViewMain


    fun searchByName(query: String) {

        var retrofit = Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/api.php/7252591128153666/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: RetrofitService = retrofit.create(RetrofitService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.searchByName(query)

            runOnUiThread {
                Log.i("HTTP", response.body().toString())
                intent
            }
        }
    }
}
