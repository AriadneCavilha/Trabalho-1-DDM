package com.example.trabalho1ddm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho1ddm.ui.adapter.FavoritosAdapter
import com.example.trabalho1ddm.R
import com.example.trabalho1ddm.ui.viewmodel.FavoritesViewModel

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.favoritos_fragment, container, false)

        // Inicializa o ViewModel
        favoritesViewModel = ViewModelProvider(requireActivity()).get(FavoritesViewModel::class.java)

        // Configura o RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observa e exibe os favoritos
        favoritesViewModel.favoriteFoods.observe(viewLifecycleOwner) { favoriteFoods ->
            recyclerView.adapter = FavoritosAdapter(favoriteFoods)
        }

        return view
    }
}
