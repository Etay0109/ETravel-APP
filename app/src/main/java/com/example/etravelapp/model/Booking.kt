package com.example.etravelapp.model

data class Booking(
    var bookingNumber: String = "",
    var destinationId: String = "",
    var city: String = "",
    var country: String = "",
    var departureDate: String = "",
    var returnDate: String = "",
    var airline: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phone: String = "",
    var price: Int = 0,
    var createdAt: Long = System.currentTimeMillis()
)