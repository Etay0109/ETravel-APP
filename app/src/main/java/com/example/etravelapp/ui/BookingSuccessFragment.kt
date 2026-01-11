package com.example.etravelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.etravelapp.R
import com.example.etravelapp.databinding.FragmentBookingSuccessBinding
import com.example.etravelapp.model.DestinationItem

class BookingSuccessFragment : Fragment() {

    // ViewBinding reference
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
        _binding = FragmentBookingSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindConfirmationData()
        setupButtons()

        binding.confirmationBTNHome.setOnClickListener {
            navigateToHome()
        }

    }

    private fun bindConfirmationData() {

        // Generate a simple booking reference
        val bookingReference = (10000000..99999999).random().toString()


        binding.confirmationLBLReference.text =
            "Booking #$bookingReference"

        // Flight details
        binding.confirmationLBLDestination.text =
            "${destination.city}, ${destination.country}"

        binding.confirmationLBLDeparture.text = departureDate
        binding.confirmationLBLReturn.text = returnDate
        binding.confirmationLBLAirline.text = airline
        binding.confirmationLBLPrice.text =
            "${destination.flightPrice}$"

        // Passenger info
        binding.confirmationLBLPassengerName.text =
            "$firstName $lastName"

        binding.confirmationLBLEmail.text = email
        binding.confirmationLBLPhone.text = phone
    }


    private fun setupButtons() {
        binding.confirmationBTNHome.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        parentFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE

        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
