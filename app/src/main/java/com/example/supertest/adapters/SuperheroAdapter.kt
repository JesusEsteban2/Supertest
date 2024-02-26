package com.example.supertest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supertest.R
import com.example.supertest.data.SuperHero

class SuperheroAdapter {

    // Crea el adapter para la reciclerView (el parámetro es la colección a visualizar en ella
    // y la función para manejar los click).
    class CustomAdapter(private var listSuperHero: List<SuperHero>, val onClickListener: (Int) -> Unit) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        /*
         * Crea el viewHolder en una inner class, que implementa el adapter.
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var imageView: ImageView
            var textView: TextView

            init {
                // Define click listener for the ViewHolder's View.
                imageView= view.findViewById(R.id.horoImage)
                textView = view.findViewById(R.id.horoText)
            }
        }

        fun updateList(list: List<SuperHero>) {
            listSuperHero = list
            notifyDataSetChanged()
        }

        /*
         * Implementa los 3 métodos abstractos del adapter.
         */
        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_superHero, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element

            val context: Context = viewHolder.itemView.context

            viewHolder.imageView.setImageBitmap(context.getDrawable(listHoroscope[position].img)
                ?.toBitmap() ?: context.getDrawable(R.drawable.aries)!!.toBitmap() )

            viewHolder.textView.text = context.getString(listHoroscope[position].name)
            //viewHolder.render(items[position]
            viewHolder.itemView.setOnClickListener {onClickListener(position)}
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = listSuperHero.size
    }
}