package com.example.trabalho1ddm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trabalho1ddm.data.api.RetrofitInstance
import com.example.trabalho1ddm.data.model.ProductResponse
import com.example.trabalho1ddm.data.model.Produto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Produto>?>()
    val products: MutableLiveData<List<Produto>?> get() = _products

    fun fetchProducts() {
        RetrofitInstance.api.getProdutos().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()
                    _products.value = responseBody?.content
                } else {
                    Log.e("API Error", "Error code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
