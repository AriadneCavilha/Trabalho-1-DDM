package com.example.trabalho1ddm.ui.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.trabalho1ddm.R
import com.example.trabalho1ddm.data.model.Produto

class ProdutoDetalhadoActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var favoriteButton: ImageView
    private var isFavorite = false
    private lateinit var product: Produto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_detalhado)

        // Recebe o produto enviado via Intent
        product = intent.getSerializableExtra("product") as Produto

        // Inicializa as views
        val productImage: ImageView = findViewById(R.id.product_image)
        val productName: TextView = findViewById(R.id.product_name)
        val productPrice: TextView = findViewById(R.id.product_price)
        val productDescription: TextView = findViewById(R.id.product_description)
        favoriteButton = findViewById(R.id.favorite_button)

        // Carrega os dados do produto
        productImage.load(product.imagem)
        productName.text = product.titulo
        productPrice.text = "R$ ${product.preco}"
        productDescription.text = "Descricao do ${product.titulo}"

        // Inicializa SharedPreferences e verifica se o produto é favorito
        sharedPreferences = getSharedPreferences("favorites", Context.MODE_PRIVATE)
        isFavorite = isProductFavorite(product.id)

        // Atualiza o ícone inicial de favorito
        updateFavoriteIcon()

        // Configura o listener do botão de favoritos
        favoriteButton.setOnClickListener {
            toggleFavorite()
        }
    }

    // Função para alternar o estado de favorito
    private fun toggleFavorite() {
        isFavorite = !isFavorite
        val editor = sharedPreferences.edit()
        val favoriteIds = sharedPreferences.getStringSet("favorite_ids", mutableSetOf()) ?: mutableSetOf()

        // Adiciona ou remove o produto da lista de favoritos
        if (isFavorite) {
            favoriteIds.add(product.id.toString())
        } else {
            favoriteIds.remove(product.id.toString())
        }

        // Salva o conjunto atualizado em SharedPreferences
        editor.putStringSet("favorite_ids", favoriteIds).apply()

        // Atualiza o ícone do botão de favorito
        updateFavoriteIcon()
    }

    // Verifica se o produto está nos favoritos
    private fun isProductFavorite(productId: Int): Boolean {
        val favoriteIds = sharedPreferences.getStringSet("favorite_ids", emptySet()) ?: emptySet()
        return favoriteIds.contains(productId.toString())
    }

    // Atualiza o ícone do botão de favorito
    private fun updateFavoriteIcon() {
        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.baseline_favorite_24) // Ícone de coração preenchido
        } else {
            favoriteButton.setImageResource(R.drawable.baseline_favorite_border_24) // Ícone de coração vazio
        }
    }

}
