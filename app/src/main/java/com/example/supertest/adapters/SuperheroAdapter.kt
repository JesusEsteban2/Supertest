package com.example.supertest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supertest.data.SuperHero
import com.example.supertest.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroAdapter(private var items:List<SuperHero> = listOf(),
                       private val onClickListener: (position:Int) -> Unit
) : RecyclerView.Adapter<SuperheroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperheroViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.render(items[position])
        holder.itemView.setOnClickListener { onClickListener(position) }
    }

    fun updateItems(results: List<SuperHero>) {
        items = results
        notifyDataSetChanged()
    }
}

class SuperheroViewHolder(val binding:ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(superhero: SuperHero) {
        binding.superName.text = superhero.name
        Picasso.get().load(superhero.httpImage.url).into(binding.superImage)
    }

}