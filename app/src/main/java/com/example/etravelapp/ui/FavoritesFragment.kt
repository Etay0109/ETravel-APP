package com.example.etravelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.etravelapp.adapters.ExploreDestinationsAdapter
import com.example.etravelapp.data.FirebaseDestinationManager
import com.example.etravelapp.databinding.FragmentFavoritesBinding
import com.example.etravelapp.model.DestinationItem
import com.example.etravelapp.utilities.DestinationType
import com.google.firebase.auth.FirebaseAuth

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoritesAdapter: ExploreDestinationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        setupRecyclerView()
        loadFavorites()

        return binding.root
    }

    private fun setupRecyclerView() {
        favoritesAdapter = ExploreDestinationsAdapter(mutableListOf())

        binding.favoritesRVList.layoutManager =
            LinearLayoutManager(requireContext())

        binding.favoritesRVList.adapter = favoritesAdapter

        favoritesAdapter.destinationCallback =
            object : com.example.etravelapp.interfaces.DestinationCallback {

                override fun destinationClicked(destination: DestinationItem) {
                    // Pass
                }

                override fun favoriteClicked(
                    destination: DestinationItem,
                    position: Int,
                    type: DestinationType
                ) {
                    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

                    destination.isFavorite = false

                    FirebaseDestinationManager.updateUserFavorite(
                        uid,
                        destination.sourceType,
                        destination.id,
                        false
                    )

                    favoritesAdapter.removeItem(position)

                    if (favoritesAdapter.itemCount == 0) {
                        binding.favoritesLAYEmpty.visibility = View.VISIBLE
                        binding.favoritesRVList.visibility = View.GONE
                    }
                }
            }
    }

    private fun loadFavorites() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val allFavorites = mutableListOf<DestinationItem>()

        // Load EXPLORE favorites
        FirebaseDestinationManager.getDestinationsWithUserFavorites(
            uid,
            DestinationType.EXPLORE
        ) { exploreList ->

            allFavorites.addAll(
                exploreList
                    .filter { it.isFavorite }
                    .onEach { it.sourceType = DestinationType.EXPLORE }
            )

            // Load POPULAR favorites
            FirebaseDestinationManager.getDestinationsWithUserFavorites(
                uid,
                DestinationType.POPULAR
            ) { popularList ->

                allFavorites.addAll(
                    popularList
                        .filter { it.isFavorite }
                        .onEach { it.sourceType = DestinationType.POPULAR }
                )

                updateUI(allFavorites)
                favoritesAdapter.updateData(allFavorites)
            }
        }
    }

    private fun updateUI(favorites: List<DestinationItem>) {
        if (favorites.isEmpty()) {
            binding.favoritesLAYEmpty.visibility = View.VISIBLE
            binding.favoritesRVList.visibility = View.GONE
        } else {
            binding.favoritesLAYEmpty.visibility = View.GONE
            binding.favoritesRVList.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
