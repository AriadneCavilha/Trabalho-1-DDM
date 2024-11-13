package com.example.trabalho1ddm.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.trabalho1ddm.ui.activity.ProdutoDetalhadoActivity
import com.example.trabalho1ddm.R
import com.example.trabalho1ddm.data.model.Produto

class ProdutoAdapter(private var products: List<Produto>) :
    RecyclerView.Adapter<ProdutoAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val produtoImagem: ImageView = view.findViewById(R.id.productImage)
        val produtoNome: TextView = view.findViewById(R.id.productName)
        val produtoPreco: TextView = view.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.produtoNome.text = product.titulo
        holder.produtoPreco.text = product.preco.toString()
        holder.produtoImagem.load(product.imagem)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProdutoDetalhadoActivity::class.java)
            intent.putExtra("product", product)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = products.size

    fun updateData(newProducts: List<Produto>) {
        products = newProducts
        notifyDataSetChanged()
    }
}
