package com.example.trabalho1ddm.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trabalho1ddm.data.model.Produto

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val _favoriteFoods = MutableLiveData<List<Produto>>()
    val favoriteFoods: LiveData<List<Produto>> get() = _favoriteFoods

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("favorites", Context.MODE_PRIVATE)

    private var allProducts: List<Produto> = listOf()

    fun setAllProducts(products: List<Produto>) {
        allProducts = products
        loadFavoriteFoods() // Carrega os favoritos filtrados
    }

    private fun loadFavoriteFoods() {

        val favoriteIds = sharedPreferences.getStringSet("favorite_ids", setOf()) ?: setOf()

        val favorites = allProducts.filter { favoriteIds.contains(it.id.toString()) }
        _favoriteFoods.value = favorites
    }
}
