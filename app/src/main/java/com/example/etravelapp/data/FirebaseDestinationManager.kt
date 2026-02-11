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

    fun checkAndSeedDestinations(   // Checks if destinations exist in Firebase, and if not, saves the initial list
        type: DestinationType,
        destinations: List<DestinationItem>,
        onComplete: () -> Unit
    ) {
        val ref = ref(type)

        ref.get().addOnSuccessListener { snapshot ->
            if (!snapshot.exists()) {

                for (destination in destinations) {
                    ref.child(destination.id).setValue(destination)
                }

                onComplete()
            } else {
                onComplete()
            }
        }
    }



    fun updateUserFavorite(    // Updates a destination from the user's favorites
        uid: String,
        type: DestinationType,
        destinationId: String,
        isFavorite: Boolean
    ) {
        val ref = database
            .getReference("users")
            .child(uid)
            .child("favorites")
            .child(type.name.lowercase())
            .child(destinationId)

        if (isFavorite) {
            ref.setValue(true)
        } else {
            ref.removeValue()
        }
    }



    fun getUserFavorites(   //Getting the user's favorite destinations
        uid: String,
        type: DestinationType,
        onResult: (Map<String, Boolean>) -> Unit
    ) {
        database
            .getReference("users")
            .child(uid)
            .child("favorites")
            .child(type.name.lowercase())
            .get()
            .addOnSuccessListener { snapshot ->

                val map = mutableMapOf<String, Boolean>()

                snapshot.children.forEach {
                    val key = it.key          // destinationId
                    val value = it.getValue(Boolean::class.java)

                    if (key != null && value != null) {
                        map[key] = value
                    }
                }

                onResult(map)
            }
    }


    fun getDestinations(    // Gets destinations from Firebase by type and returns them as a list through a callback
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

                for (destination in destinations) {
                    destination.sourceType = type
                    destination.isFavorite = favorites[destination.id] == true
                }

                onResult(destinations)
            }
        }
    }


}



