package com.example.etravelapp.model

import java.io.Serializable

data class Attraction(
    var name: String = "",
    var poster: String = ""
) : Serializable {

    constructor() : this(
        name = "",
        poster = ""
    )
}
