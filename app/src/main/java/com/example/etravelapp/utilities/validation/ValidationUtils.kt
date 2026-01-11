package com.example.etravelapp.utilities.validation

import java.text.SimpleDateFormat
import java.util.Locale

object ValidationUtils {

    fun isNameValid(name: String): Boolean {
        return name.isNotEmpty()
    }

    fun isEmailValid(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    fun isPhoneValid(phone: String): Boolean {
        return phone.length in 9..10
    }

    fun isIdValid(id: String): Boolean {
        return id.length == 9
    }

    fun isCardNumberValid(cardNumber: String): Boolean {
        return cardNumber.length == 16
    }

    fun isCvvValid(cvv: String): Boolean {
        return cvv.length == 3
    }

    fun isExpiryValid(expiry: String): Boolean {
        return expiry.isNotEmpty() && expiry.length == 4
    }

    fun areDatesValid(departure: String, returnDate: String): Boolean {
        return try {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dep = formatter.parse(departure)
            val ret = formatter.parse(returnDate)

            ret != null && dep != null && ret.after(dep)
        } catch (e: Exception) {
            false
        }
    }
}
