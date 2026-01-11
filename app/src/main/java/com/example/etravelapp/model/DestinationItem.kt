package com.example.etravelapp.model

import java.io.Serializable
import com.example.etravelapp.utilities.DestinationType



data class DestinationItem(
    var id: String = "",
    var poster: String,
    var city: String,
    var country: String,
    var description: String = "",
    var attractions: List<Attraction> = emptyList(),
    var flightTime: String? = null,
    var bestSeason: String = "",
    var avgCostPerDay: String = "",
    var language: String = "",
    var flightPrice: Int = 0,
    var isFavorite: Boolean = false,
    var sourceType: DestinationType = DestinationType.EXPLORE
) : Serializable{

    constructor() : this(
        id = "",
        poster = "",
        city = "",
        country = "",
        description = "",
        attractions = emptyList(),
        flightTime = null,
        bestSeason = "",
        avgCostPerDay = "",
        language = "",
        flightPrice = 0,
        isFavorite = false
    )

    fun toggleFavorite() = apply { isFavorite = !isFavorite }

    class Builder(
        private var id: String = "",
        private var poster: String = "",
        private var city: String = "",
        private var country: String = "",
        private var description: String = "",
        private var attractions: List<Attraction> = emptyList(),
        private var flightTime: String? = null,
        private var bestSeason: String = "",
        private var avgCostPerDay: String = "",
        private var language: String = "",
        private var flightPrice: Int = 0,
        private var isFavorite: Boolean = false
    ) {
        fun id(id: String) = apply { this.id = id }
        fun poster(poster: String) = apply { this.poster = poster }
        fun city(city: String) = apply { this.city = city }
        fun country(country: String) = apply { this.country = country }
        fun description(description: String) = apply { this.description = description }
        fun attractions(attractions: List<Attraction>) = apply { this.attractions = attractions }
        fun flightTime(flightTime: String) = apply { this.flightTime = flightTime }
        fun bestSeason(bestSeason: String) = apply { this.bestSeason = bestSeason }
        fun avgCostPerDay(avgCostPerDay: String) = apply { this.avgCostPerDay = avgCostPerDay }
        fun language(language: String) = apply { this.language = language }
        fun flightPrice(flightPrice: Int) = apply { this.flightPrice = flightPrice }
        fun isFavorite(isFavorite: Boolean) = apply { this.isFavorite = isFavorite }

        fun build(): DestinationItem {
            return DestinationItem(
                id = id,
                poster = poster,
                city = city,
                country = country,
                description = description,
                attractions = attractions,
                flightTime = flightTime,
                bestSeason = bestSeason,
                avgCostPerDay = avgCostPerDay,
                language = language,
                flightPrice = flightPrice,
                isFavorite = isFavorite
            )
        }
    }
}
