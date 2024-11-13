package com.example.trabalho1ddm.ui.fragment

import com.example.trabalho1ddm.ui.viewmodel.MainViewModel
import com.example.trabalho1ddm.ui.adapter.ProdutoAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho1ddm.R
import com.example.trabalho1ddm.ui.viewmodel.FavoritesViewModel
import com.google.firebase.auth.FirebaseAuth

class TelaPrincipalFragment : Fragment() {
    private lateinit var productViewModel: MainViewModel
    private lateinit var productAdapter: ProdutoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var favoritesViewModel: FavoritesViewModel  // Compartilha com a Activity
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.activity_principal, container, false)
        auth = FirebaseAuth.getInstance()

        recyclerView = view.findViewById(R.id.recyclerViewProdutos)
        productAdapter = ProdutoAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = productAdapter

        productViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        favoritesViewModel = ViewModelProvider(requireActivity()).get(FavoritesViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Carrega os produtos
        productViewModel.fetchProducts()

        // Observa a lista completa de produtos e atualiza o FavoritesViewModel
        productViewModel.products.observe(viewLifecycleOwner) { products ->
            if (products != null) {
                // Atualiza o RecyclerView com todos os produtos
                productAdapter.updateData(products)

                // Passa a lista completa de produtos para o FavoritesViewModel
                favoritesViewModel.setAllProducts(products)
            }
        }
    }
}
