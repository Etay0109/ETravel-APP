package com.example.etravelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.etravelapp.R
import com.example.etravelapp.adapters.PopularDestinationsAdapter
import com.example.etravelapp.adapters.ExploreDestinationsAdapter
import com.example.etravelapp.data.DestinationDataManager
import com.example.etravelapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.example.etravelapp.data.FirebaseDestinationManager
import com.example.etravelapp.model.DestinationItem
import com.example.etravelapp.interfaces.DestinationCallback
import com.example.etravelapp.utilities.DestinationType


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var popularAdapter: PopularDestinationsAdapter
    private lateinit var exploreAdapter: ExploreDestinationsAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        // Load initial data to Firebase if it does not exist
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return binding.root
        seedIfNeeded(uid)

        // Show user's name and adapt welcome text & layout direction to the device language
        val user = FirebaseAuth.getInstance().currentUser
        val displayName = user?.displayName ?: "Traveler"
        binding.homeLBLUsername.text = displayName
        binding.homeLBLWelcome.setText(R.string.home_welcome)

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupPopularRecycler(uid: String) {
        binding.homeRVPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        popularAdapter = PopularDestinationsAdapter(mutableListOf())
        binding.homeRVPopular.adapter = popularAdapter

        popularAdapter.destinationCallback = object : DestinationCallback {
            override fun destinationClicked(destination: DestinationItem) {
                openDestinationDetails(destination)
            }
            override fun favoriteClicked(
                destination: DestinationItem,
                position: Int,
                type: DestinationType
            ) {
                destination.toggleFavorite()
                FirebaseDestinationManager.updateUserFavorite(
                    uid,
                    DestinationType.POPULAR,
                    position,
                    destination.isFavorite
                )
                popularAdapter.notifyItemChanged(position)
            }
        }

        FirebaseDestinationManager.getDestinationsWithUserFavorites(
            uid,
            DestinationType.POPULAR
        ) { destinations ->
            popularAdapter.updateData(destinations)

        }
    }


    private fun setupExploreRecycler(uid: String) {
        binding.homeRVExplore.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        exploreAdapter = ExploreDestinationsAdapter(mutableListOf())
        binding.homeRVExplore.adapter = exploreAdapter

        exploreAdapter.destinationCallback = object : DestinationCallback {
            override fun destinationClicked(destination: DestinationItem) {
                openDestinationDetails(destination)
            }

            override fun favoriteClicked(
                destination: DestinationItem,
                position: Int,
                type: DestinationType
            ) {
                destination.toggleFavorite()
                FirebaseDestinationManager.updateUserFavorite(
                    uid,
                    DestinationType.EXPLORE,
                    position,
                    destination.isFavorite
                )

                exploreAdapter.notifyItemChanged(position)
            }
        }

        FirebaseDestinationManager.getDestinationsWithUserFavorites(
            uid,
            DestinationType.EXPLORE
        ) { destinations ->

            exploreAdapter.updateData(destinations)

        }

    }

    private fun openDestinationDetails(destination: DestinationItem) {
        val fragment = DestinationDetailsFragment()

        val bundle = Bundle()
        bundle.putSerializable("destination", destination)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_LAY_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun seedIfNeeded(uid: String) {
        FirebaseDestinationManager.checkAndSeedDestinations(
            DestinationType.POPULAR,
            DestinationDataManager.getPopularDestinations()
        ) {
            FirebaseDestinationManager.checkAndSeedDestinations(
                DestinationType.EXPLORE,
                DestinationDataManager.getExploreDestinations()
            ) {
                // Data is ready → load UI
                setupPopularRecycler(uid)
                setupExploreRecycler(uid)
            }
        }
    }




}
