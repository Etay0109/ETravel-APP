package com.example.etravelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.etravelapp.R
import com.example.etravelapp.databinding.FragmentBookingSuccessBinding
import com.example.etravelapp.model.DestinationItem

class BookingSuccessFragment : Fragment() {

    private var _binding: FragmentBookingSuccessBinding? = null
    private val binding get() = _binding!!

    private lateinit var destination: DestinationItem
    private lateinit var departureDate: String
    private lateinit var returnDate: String
    private lateinit var airline: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var phone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            destination = it.getSerializable("destination") as DestinationItem
            departureDate = it.getString("departureDate", "")
            returnDate = it.getString("returnDate", "")
            airline = it.getString("airline", "")
            firstName = it.getString("firstName", "")
            lastName = it.getString("lastName", "")
            email = it.getString("email", "")
            phone = it.getString("phone", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingSuccessBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindConfirmationData()
        setupButtons()
    }

    private fun bindConfirmationData() {    // Displays the booking confirmation details on the screen

        val bookingReference = (10000000..99999999).random().toString()

        binding.confirmationLBLReference.text =
            "Booking #$bookingReference"

        binding.confirmationLBLDestination.text =
            "${destination.city}, ${destination.country}"

        binding.confirmationLBLDeparture.text = departureDate
        binding.confirmationLBLReturn.text = returnDate
        binding.confirmationLBLAirline.text = airline
        binding.confirmationLBLPrice.text =
            "${destination.flightPrice}$"

        binding.confirmationLBLPassengerName.text =
            "$firstName $lastName"

        binding.confirmationLBLEmail.text = email
        binding.confirmationLBLPhone.text = phone
    }

    private fun setupButtons() {    // Handles the home button click
        binding.confirmationBTNHome.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {   // Navigates back to the home screen safely
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_LAY_container, HomeFragment())
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}