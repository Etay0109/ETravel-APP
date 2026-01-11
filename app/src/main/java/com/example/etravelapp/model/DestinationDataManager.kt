package com.example.etravelapp.data

import com.example.etravelapp.model.Attraction
import com.example.etravelapp.model.DestinationItem

object DestinationDataManager {

    fun getPopularDestinations(): List<DestinationItem> {
        val destinations = mutableListOf<DestinationItem>()

        destinations.add(
            DestinationItem.Builder()
                .city("Paris")
                .country("France")
                .poster("https://images.unsplash.com/photo-1511739001486-6bfe10ce785f?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .flightTime("5h 00m")
                .bestSeason("Spring")
                .avgCostPerDay("180$/day")
                .language("French")
                .flightPrice(450)
                .description(
                    "Paris, known as the 'City of Light', is one of the most beautiful cities in the world. " +
                        "It is famous for its amazing food, fashion, and art. " +
                        "You can visit iconic places like the Eiffel Tower and the Louvre Museum. " +
                        "Whether you walk along the Seine River or enjoy a coffee in a small cafe, " +
                        "Paris offers a magical experience for everyone.")
                .attractions(
                    listOf(
                        Attraction(
                            name = "Eiffel Tower",
                            poster ="https://images.unsplash.com/photo-1604175287072-b5e71423060c?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Louvre Museum",
                            poster = "https://images.unsplash.com/photo-1551634979-2b11f8c946fe?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Arc de Triomphe",
                            poster = "https://plus.unsplash.com/premium_photo-1694475534209-4384b624cb95?q=80&w=850&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Sacré-Cœur Basilica",
                            poster = "https://plus.unsplash.com/premium_photo-1694475328996-8f4cad8aac03?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                    )
                )

                .build()
        )

        destinations.add(
            DestinationItem.Builder()
                .city("London")
                .country("United Kingdom")
                .poster("https://images.unsplash.com/photo-1569865867048-34cfce8d58fe?q=80&w=756&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .flightTime("5h 30m")
                .bestSeason("Summer")
                .avgCostPerDay("200$/day")
                .language("English")
                .flightPrice(520)
                .description(
                    "London, the capital of England, is a vibrant city where history meets modern culture." +
                            " It is famous for iconic landmarks like Big Ben," +
                            " the London Eye, and Buckingham Palace." +
                            " Whether you take a ride on a red double-decker bus or enjoy a walk along the River Thames," +
                            " London offers an unforgettable adventure"
                )
                .attractions(
                    listOf(
                        Attraction(
                            name = "Big Ben & Parliament",
                            poster ="https://images.unsplash.com/photo-1692894247000-e0a2b26090c4?q=80&w=776&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "London Eye",
                            poster = "https://images.unsplash.com/photo-1545853332-147d5073187e?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Tower Bridge",
                            poster = "https://images.unsplash.com/photo-1691611076742-ab255e24a4d2?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Buckingham Palace",
                            poster = "https://images.unsplash.com/photo-1682129040258-9fb699614d74?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                    )
                )
                .build()
        )

        destinations.add(
            DestinationItem.Builder()
                .city("Rome")
                .country("Italy")
                .poster("https://images.unsplash.com/photo-1645649644176-275e96b2af7f?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .flightTime("3h 45m")
                .bestSeason("Spring")
                .avgCostPerDay("150$/day")
                .language("Italian")
                .flightPrice(280)
                .description(
                    "Rome, known as the 'Eternal City'," +
                            " is a living museum of history and culture." +
                            " It is famous for ancient landmarks like the Colosseum and the Vatican City." +
                            " Whether you toss a coin into the Trevi Fountain or enjoy authentic pizza and gelato," +
                            " Rome offers a journey back in time you will never forget"
                )
                .attractions(
                    listOf(
                        Attraction(
                            name = "The Colosseum",
                            poster ="https://images.unsplash.com/photo-1724608993265-82690cc501cd?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Vatican City & St. Peter's",
                            poster = "https://plus.unsplash.com/premium_photo-1661962860425-373f38f8e6f4?q=80&w=870&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Trevi Fountain",
                            poster = "https://images.unsplash.com/photo-1584999872814-569a6b02a2b4?q=80&w=627&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "The Pantheon",
                            poster = "https://images.unsplash.com/photo-1676371811453-a66ee478757c?q=80&w=870&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                    )
                )
                .build()
        )

        destinations.add(
            DestinationItem.Builder()
                .city("New York")
                .country("USA")
                .poster("https://images.unsplash.com/photo-1527267207156-3372670819dc?q=80&w=776&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .flightTime("12h 00m")
                .bestSeason("Autumn")
                .avgCostPerDay("250$/day")
                .language("English")
                .flightPrice(850)
                .description(
                    "New York City, known as 'The Big Apple'," +
                            " is a vibrant metropolis that never sleeps." +
                            " It is famous for iconic landmarks like the Statue of Liberty," +
                            " Times Square, and Central Park. Whether you catch a Broadway show or enjoy the view from the Empire State Building," +
                            " New York offers an energy you won't find anywhere else"
                )
                .attractions(
                    listOf(
                        Attraction(
                            name = "Statue of Liberty",
                            poster ="https://plus.unsplash.com/premium_photo-1681803531285-75db948035d3?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Central Park",
                            poster = "https://images.unsplash.com/photo-1631499545782-7d09b3a7dcf7?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Times Square",
                            poster = "https://images.unsplash.com/photo-1595901688281-9cef114adb0b?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Brooklyn Bridge",
                            poster = "https://images.unsplash.com/photo-1610879264747-c94eec8f5276?q=80&w=776&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                    )
                )
                .build()
        )

        destinations.add(
            DestinationItem.Builder()
                .city("Tokyo")
                .country("Japan")
                .poster("https://plus.unsplash.com/premium_photo-1722872944372-3fac47ddb569?q=80&w=987&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .flightTime("11h 30m")
                .bestSeason("Spring")
                .avgCostPerDay("160$/day")
                .language("Japanese")
                .flightPrice(1200)
                .description(
                    "Tokyo, Japan's bustling capital," +
                            " is a dazzling mix of the ultramodern and the traditional." +
                            " It is famous for its neon-lit streets, historic temples, and incredible food scene." +
                            " Whether you cross the famous Shibuya Crossing or find peace at the Senso-ji Temple," +
                            " Tokyo offers a unique adventure for every traveler."
                )
                .attractions(
                    listOf(
                        Attraction(
                            name = "Senso-ji Temple",
                            poster ="https://images.unsplash.com/photo-1740950186277-488b24ad3da8?q=80&w=870&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Shibuya Crossing",
                            poster = "https://images.unsplash.com/photo-1715607817921-aa6417a19dbd?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Tokyo Tower",
                            poster = "https://images.unsplash.com/photo-1536098561742-ca998e48cbcc?q=80&w=1072&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Meiji Jingu Shrine",
                            poster = "https://images.unsplash.com/photo-1764069347328-687152bc2bf7?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                    )
                )
                .build()
        )

        return destinations
    }

    fun getExploreDestinations(): List<DestinationItem> {
        val destinations = mutableListOf<DestinationItem>()

        destinations.add(
            DestinationItem.Builder()
                .city("Barcelona")
                .country("Spain")
                .poster("https://images.unsplash.com/photo-1559329591-1c7ce35fc8a2?q=80&w=870&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .flightTime("4h 45m")
                .bestSeason("Summer")
                .avgCostPerDay("140$/day")
                .language("Spanish")
                .flightPrice(350)
                .description(
                    "Barcelona, the vibrant capital of Catalonia," +
                            " is famous for its unique architecture and Mediterranean charm." +
                            " It is known for Gaudí's masterpieces like the Sagrada Família and Park Güell." +
                            " Whether you relax on the beach, explore the Gothic Quarter," +
                            " or taste tapas in a local market, Barcelona offers a perfect mix of culture and fun"
                )
                .attractions(
                    listOf(
                        Attraction(
                            name = "Sagrada Família",
                            poster ="https://images.unsplash.com/photo-1722863380905-539ae092fc5f?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Park Güell",
                            poster = "https://images.unsplash.com/photo-1588664724669-dc41c5d1de96?q=80&w=772&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "La Boqueria Market",
                            poster = "https://images.unsplash.com/photo-1711089550883-659fe38f32b2?q=80&w=627&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Casa Batlló",
                            poster = "https://images.unsplash.com/photo-1579282240050-352db0a14c21?q=80&w=704&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                    )
                )
                .build()
        )

        destinations.add(
            DestinationItem.Builder()
                .city("Dubai")
                .country("United Arab Emirates")
                .poster("https://images.unsplash.com/photo-1634007626524-f47fa37810a7?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .flightTime("3h 15m")
                .bestSeason("Winter")
                .avgCostPerDay("220$/day")
                .language("Arabic")
                .flightPrice(300)
                .description(
                    "Dubai, the jewel of the Middle East," +
                            " is a city of the future where luxury meets adventure." +
                            " It is famous for the world's tallest building," +
                            " the Burj Khalifa, and vast shopping malls." +
                            " Whether you explore the golden desert dunes or relax on Palm Jumeirah, Dubai offers a dazzling experience like no other"
                )
                .attractions(
                    listOf(
                        Attraction(
                            name = "Burj Khalifa",
                            poster ="https://images.unsplash.com/photo-1582120031356-35f21bf61055?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "The Dubai Mall",
                            poster = "https://images.unsplash.com/photo-1557336901-4a4e829af280?q=80&w=872&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Palm Jumeirah",
                            poster = "https://images.unsplash.com/photo-1518684079-3c830dcef090?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Desert Safari",
                            poster = "https://images.unsplash.com/photo-1695878868496-fcbd6ef47f57?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                    )
                )
                .build()
        )

        destinations.add(
            DestinationItem.Builder()
                .city("Athens")
                .country("Greece")
                .poster("https://images.unsplash.com/photo-1668093375941-ccfe6c34c89c?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .flightTime("2h 15m")
                .bestSeason("Spring")
                .avgCostPerDay("100$/day")
                .language("Greek")
                .flightPrice(180)
                .description(
                    "Athens, the historical capital of Greece," +
                            " is the birthplace of democracy and Western civilization." +
                            " It is famous for ancient ruins like the majestic Acropolis and the Parthenon." +
                            " Whether you explore the charming streets of Plaka or enjoy authentic Greek food," +
                            " Athens offers a walk through history under the Mediterranean sun"
                )
                .attractions(
                    listOf(
                        Attraction(
                            name = "The Acropolis",
                            poster ="https://images.unsplash.com/photo-1721250150605-6f43bae03fce?q=80&w=870&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Plaka District",
                            poster = "https://images.unsplash.com/photo-1760348024138-39545d214f75?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Acropolis Museum",
                            poster = "https://images.unsplash.com/photo-1629097671342-16602fc04942?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Panathenaic Stadium",
                            poster = "https://images.unsplash.com/photo-1751127796818-954eb01dc59a?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                    )
                )
                .build()
        )

        destinations.add(
            DestinationItem.Builder()
                .city("Berlin")
                .country("Germany")
                .poster("https://images.unsplash.com/photo-1566404791232-af9fe0ae8f8b?q=80&w=872&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .flightTime("4h 30m")
                .bestSeason("Summer")
                .avgCostPerDay("130$/day")
                .language("German")
                .flightPrice(320)
                .description(
                    "Berlin, the capital of Germany," +
                            " is a city where history meets modern cool." +
                            " It is famous for landmarks like the Brandenburg Gate and the remains of the Berlin Wall." +
                            " Whether you explore world-class museums or enjoy the vibrant nightlife," +
                            " Berlin offers an exciting mix of the old and the new."
                )
                .attractions(
                    listOf(
                        Attraction(
                            name = "Brandenburg Gate",
                            poster ="https://images.unsplash.com/photo-1585321962899-e956da0b7e5a?q=80&w=748&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "East Side Gallery",
                            poster = "https://images.unsplash.com/photo-1646489752489-e5831112c767?q=80&w=627&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Reichstag Building",
                            poster = "https://plus.unsplash.com/premium_photo-1694475270589-6c0823831e5d?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Museum Island",
                            poster = "https://images.unsplash.com/photo-1747119421191-2d73381e46b8?q=80&w=870&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                    )
                )
                .build()
        )

        destinations.add(
            DestinationItem.Builder()
                .city("Bangkok")
                .country("Thailand")
                .poster("https://plus.unsplash.com/premium_photo-1693238111767-c6b47ba92f40?q=80&w=774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .flightTime("11h 20m")
                .bestSeason("Winter")
                .avgCostPerDay("70$/day")
                .language("Thai")
                .flightPrice(900)
                .description(
                    "Bangkok, Thailand's vibrant capital," +
                            " is known for its ornate shrines and busy street life." +
                            " It is famous for the majestic Grand Palace and bustling floating markets." +
                            " Whether you taste world-class street food or take a ride in a tuk-tuk," +
                            " Bangkok offers an exciting mix of ancient tradition and modern energy"
                )
                .attractions(
                    listOf(
                        Attraction(
                            name = "The Grand Palace",
                            poster ="https://plus.unsplash.com/premium_photo-1694475226740-236c5c28548b?q=80&w=2340&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Wat Arun (Temple of Dawn)",
                            poster = "https://images.unsplash.com/photo-1762950297584-23ea657c1bf8?q=80&w=870&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Wat Pho",
                            poster = "https://images.unsplash.com/photo-1660964013458-aa2d7da27e89?q=80&w=1160&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                        Attraction(
                            name = "Floating Market",
                            poster = "https://images.unsplash.com/photo-1614888901558-dd1ef11bada3?q=80&w=870&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        ),
                    )
                )
                .build()
        )

        return destinations
    }

    fun getAllDestinations(): List<DestinationItem> {
        return getPopularDestinations() + getExploreDestinations()
    }



}
