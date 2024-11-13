package com.example.trabalho1ddm.data.api;

import com.example.trabalho1ddm.data.model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("comidas")
    Call<ProductResponse> getProdutos();
}
