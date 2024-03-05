package com.example.supertest.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.supertest.adapters.SuperheroAdapter
import com.example.supertest.data.RetrofitBuilder
import com.example.supertest.data.SuperHero
import com.example.supertest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



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

        //Captura el intro en el cuadro de texto para realizar la busqueda en vez de nueva línea
        binding.editSearch.setOnEditorActionListener(OnEditorActionListener { textView, actionId, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN) {
                binding.findButton.requestFocus()
                searchSuperheroes(binding.editSearch.text.toString())
                return@OnEditorActionListener true
            }
            false
        })


        binding.findButton.setOnClickListener{searchSuperheroes(binding.editSearch.text.toString())}
    }

    private fun onItemClickListener(position:Int) {
        val superhero: SuperHero = superheroList[position]
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("EXTRA_ID", superhero.id)
        intent.putExtra("EXTRA_NAME", superhero.name)
        intent.putExtra("EXTRA_IMAGE", superhero.httpImage.url)
        startActivity(intent)
        //Toast.makeText(this, getString(horoscope.name), Toast.LENGTH_LONG).show()
    }

    private fun searchSuperheroes(query: String) {
        //binding.progress.visibility = View.VISIBLE

        //ocultar el teclado
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editSearch.getWindowToken(), 0)

        //si buscas en el searchview del menu, se puede usar para ocultar el teclado.
        // binding.editSearch.clearFocus()

        val service=RetrofitBuilder.getService()

        // Llamada en 2º plano
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.searchByName(query)

            runOnUiThread {
                // Modificar UI
                if ((response.body()!!.listSuperHero != null ) && response.isSuccessful == true) {
                    Log.i("HTTP", "respuesta correcta :)")
                    Log.i ("HTTP", response.body().toString())
                    superheroList = response.body()!!.listSuperHero
                    adapter.updateItems(superheroList)
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                }
            }

        }

    }
}
