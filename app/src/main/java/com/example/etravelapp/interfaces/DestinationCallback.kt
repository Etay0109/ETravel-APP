package com.example.etravelapp.interfaces

import com.example.etravelapp.model.DestinationItem
import com.example.etravelapp.utilities.DestinationType

interface DestinationCallback {
    fun destinationClicked(destination: DestinationItem)
    fun favoriteClicked(
        destination: DestinationItem,
        position: Int,
        type: DestinationType
    )

}