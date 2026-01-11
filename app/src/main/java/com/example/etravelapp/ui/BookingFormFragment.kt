package com.example.etravelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.etravelapp.R
import com.example.etravelapp.databinding.FragmentBookingFormBinding
import com.example.etravelapp.model.DestinationItem
import com.example.etravelapp.utilities.SignalManager
import com.example.etravelapp.utilities.SingleSoundPlayer
import com.example.etravelapp.utilities.SplashScreenManager
import androidx.lifecycle.lifecycleScope
import com.example.etravelapp.utilities.validation.ValidationUtils
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class BookingFormFragment : Fragment() {


    private lateinit var splashManager: SplashScreenManager
    private lateinit var bookingJob: Job
    private lateinit var destination: DestinationItem

    private var _binding: FragmentBookingFormBinding? = null
    private val binding get() = _binding!!
    private var isBookingInProgress = false


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
        _binding = FragmentBookingFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindSummaryData()
        setupSplashManager()
        setupAirlineDropdown()
        setupDatePickers()

        binding.bookingBTNSubmit.setOnClickListener {
            submitBooking()
        }
    }


    private fun bindSummaryData() {
        binding.bookingLBLDestination.text =
            "Flight to ${destination.city}"

        binding.bookingLBLPrice.text =
            "${destination.flightPrice}$"
    }

    private fun setupSplashManager() {
        splashManager = SplashScreenManager(
            overlayView = binding.bookingOVERLAYLoading,
            lottieView = binding.bookingLOTTIELoading,
            onFinished = {
                // Play success sound
                SingleSoundPlayer(requireContext())
                    .playSound(R.raw.ca_ching)
                isBookingInProgress = false

                navigateToSuccessScreen()
            }
        )
    }

    private fun setupAirlineDropdown() {

        val airlines = listOf(
            "El Al",
            "Arkia",
            "Israir",
            "Lufthansa",
            "Air France",
            "British Airways"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            airlines
        )

        binding.bookingEDTAirline.setAdapter(adapter)

        binding.bookingEDTAirline.setOnClickListener {
            binding.bookingEDTAirline.showDropDown()
        }
    }

    private fun setupDatePickers() {
        binding.bookingEDTDepartureDate.setOnClickListener {
            showDatePicker(binding.bookingEDTDepartureDate)
        }

        binding.bookingEDTReturnDate.setOnClickListener {
            showDatePicker(binding.bookingEDTReturnDate)
        }
    }

    private fun showDatePicker(targetField: TextView) { // Opens a calendar and sets the selected date into the given field

        val datePicker = MaterialDatePicker.Builder
            .datePicker()
            .setTitleText("Select Date")
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            val formattedDate = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            ).format(Date(selection))

            targetField.text = formattedDate
        }

        datePicker.show(parentFragmentManager, "DATE_PICKER")
    }




    private fun submitBooking() {

        if (isBookingInProgress) return
        if (!isFormValid()) {
            SignalManager.getInstance().vibrate()
            return
        }
        isBookingInProgress = true
        startBookingProcess()
    }

    private fun isFormValid(): Boolean {

        if (!ValidationUtils.isNameValid(binding.bookingEDTFirstName.text.toString())) {
            return showError("Invalid first name")
        }

        if (!ValidationUtils.isNameValid(binding.bookingEDTLastName.text.toString())) {
            return showError("Invalid last name")
        }

        if (!ValidationUtils.isEmailValid(binding.bookingEDTEmail.text.toString())) {
            return showError("Invalid email address")
        }

        if (!ValidationUtils.isPhoneValid(binding.bookingEDTPhone.text.toString())) {
            return showError("Invalid phone number")
        }

        if (!ValidationUtils.isIdValid(binding.bookingEDTIdNumber.text.toString())) {
            return showError("Invalid ID number")
        }

        if (binding.bookingEDTAirline.text.toString().isEmpty()) {
            return showError("Please select an airline")
        }

        if (!ValidationUtils.areDatesValid(
                binding.bookingEDTDepartureDate.text.toString(),
                binding.bookingEDTReturnDate.text.toString()
            )) {
            return showError("Return date must be after departure date")
        }

        if (!ValidationUtils.isCardNumberValid(binding.bookingEDTCardNumber.text.toString())) {
            return showError("Invalid card number")
        }

        if (!ValidationUtils.isCvvValid(binding.bookingEDTCvv.text.toString())) {
            return showError("Invalid CVV")
        }

        if (!ValidationUtils.isExpiryValid(binding.bookingEDTExpiry.text.toString())) {
            return showError("Invalid expiry date")
        }
        return true
    }

    private fun showError(message: String): Boolean {
        SignalManager.getInstance().vibrate()
        SignalManager.getInstance().toast(message)
        return false
    }


    private fun startBookingProcess() {

        splashManager.start()

        bookingJob = viewLifecycleOwner.lifecycleScope.launch {

            delay(2000)

            splashManager.stop()
        }
    }

    private fun navigateToSuccessScreen() {

        val fragment = BookingSuccessFragment()

        fragment.arguments = Bundle().apply {
            putSerializable("destination", destination)
            putString("departureDate", binding.bookingEDTDepartureDate.text.toString())
            putString("returnDate", binding.bookingEDTReturnDate.text.toString())
            putString("airline", binding.bookingEDTAirline.text.toString())

            // Passenger info
            putString("firstName", binding.bookingEDTFirstName.text.toString())
            putString("lastName", binding.bookingEDTLastName.text.toString())
            putString("email", binding.bookingEDTEmail.text.toString())
            putString("phone", binding.bookingEDTPhone.text.toString())
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_LAY_container, fragment)
            .addToBackStack(null)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()

        if (::bookingJob.isInitialized) {
            bookingJob.cancel()
        }

        _binding = null
    }

}
