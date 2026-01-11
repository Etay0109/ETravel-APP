package com.example.etravelapp.data

import com.example.etravelapp.model.DestinationItem
import com.example.etravelapp.utilities.DestinationType
import com.google.firebase.database.FirebaseDatabase

object FirebaseDestinationManager {

    private val database = FirebaseDatabase.getInstance(
        "https://etravelapp-fae5b-default-rtdb.europe-west1.firebasedatabase.app"
    )

    private fun ref(type: DestinationType) =
        database.getReference("destinations/${type.name.lowercase()}")

    fun checkAndSeedDestinations(
        type: DestinationType,
        destinations: List<DestinationItem>,
        onComplete: () -> Unit
    ) {
        val ref = ref(type)
        ref.get().addOnSuccessListener { snapshot ->
            if (!snapshot.exists()) {
                ref.setValue(destinations).addOnSuccessListener {
                    onComplete()
                }
            } else {
                onComplete()
            }
        }
    }


    fun updateUserFavorite( //Saving the user's favorite destinations
        uid: String,
        type: DestinationType,
        position: Int,
        isFavorite: Boolean
    ) {
        database
            .getReference("users")
            .child(uid)
            .child("favorites")
            .child(type.name.lowercase())
            .child(position.toString())
            .setValue(isFavorite)
    }

    fun getUserFavorites(   //Getting the user's favorite destinations
        uid: String,
        type: DestinationType,
        onResult: (Map<Int, Boolean>) -> Unit
    ) {
        database
            .getReference("users")
            .child(uid)
            .child("favorites")
            .child(type.name.lowercase())
            .get()
            .addOnSuccessListener { snapshot ->

                val map = mutableMapOf<Int, Boolean>()

                snapshot.children.forEach {
                    val key = it.key?.toIntOrNull()
                    val value = it.getValue(Boolean::class.java)

                    if (key != null && value != null) {
                        map[key] = value
                    }
                }

                onResult(map)
            }
    }

    fun getDestinations(
        type: DestinationType,
        onResult: (List<DestinationItem>) -> Unit
    ) {
        ref(type).get().addOnSuccessListener { snapshot ->
            val list = mutableListOf<DestinationItem>()
            snapshot.children.forEach {
                it.getValue(DestinationItem::class.java)?.let(list::add)
            }
            onResult(list)
        }
    }

    fun getDestinationsWithUserFavorites(   // Returns destinations with the user's favorites applied
        uid: String,
        type: DestinationType,
        onResult: (List<DestinationItem>) -> Unit
    ) {
        getDestinations(type) { destinations ->

            getUserFavorites(uid, type) { favorites ->

                for (i in destinations.indices) {
                    destinations[i].isFavorite = favorites[i] == true
                }

                onResult(destinations)
            }
        }
    }


}



