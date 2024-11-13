package com.example.trabalho1ddm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.trabalho1ddm.R
import com.example.trabalho1ddm.data.model.Produto

class FavoritosAdapter(private val favoriteItems: List<Produto>) : RecyclerView.Adapter<FavoritosAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val produto = favoriteItems[position]
        holder.bind(produto)
    }

    override fun getItemCount(): Int = favoriteItems.size

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val favoriteImage: ImageView = itemView.findViewById(R.id.favorite_image)
        private val favoriteName: TextView = itemView.findViewById(R.id.favorite_name)

        fun bind(produto: Produto) {
            favoriteName.text = produto.titulo
            favoriteImage.load(produto.imagem) // Usando Coil para carregar imagem
        }
    }
}
