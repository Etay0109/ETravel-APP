package com.example.etravelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.etravelapp.R
import com.example.etravelapp.adapters.AttractionsAdapter
import com.example.etravelapp.databinding.FragmentDestinationDetailsBinding
import com.example.etravelapp.model.DestinationItem
import com.example.etravelapp.utilities.ImageLoader

class DestinationDetailsFragment : Fragment() {

    private var _binding: FragmentDestinationDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var destination: DestinationItem
    private lateinit var attractionsAdapter: AttractionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        destination =
            arguments?.getSerializable("destination") as DestinationItem
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDestinationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAttractionsRecycler()
        bindDestinationData()
        binding.detailsBTNBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.detailsBTNBook.setOnClickListener {
            navigateToBookingForm()
        }
    }

    private fun setupAttractionsRecycler() { // Setup attractions recycler
        attractionsAdapter = AttractionsAdapter(mutableListOf())

        binding.detailsRVAttractions.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = attractionsAdapter
        }
    }

    private fun bindDestinationData() {

        // Header image
        ImageLoader.getInstance().loadImage(
            destination.poster,
            binding.detailsIMGHeader
        )

        // Basic info
        binding.detailsLBLCity.text = destination.city
        binding.detailsLBLCountry.text = destination.country
        binding.detailsLBLDescription.text = destination.description
        binding.detailsLBLFlightTime.text =
            destination.flightTime ?: "-"
        binding.detailsLBLBestSeason.text = destination.bestSeason
        binding.detailsLBLAvgCost.text = destination.avgCostPerDay
        binding.detailsLBLLanguage.text = destination.language


        // Attractions
        attractionsAdapter.updateData(destination.attractions)
    }

    private fun navigateToBookingForm() {
        val bookingFragment = BookingFormFragment()

        bookingFragment.arguments = Bundle().apply {
            putSerializable("destination", destination)
        }

        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_LAY_container, bookingFragment)
            .addToBackStack(null)
            .commit()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
