package com.example.earthquakeinformation.data.domain

import com.example.earthquakeinformation.data.database.entities.EarthquakeEntity
import com.example.earthquakeinformation.data.model.EarthquakeApiResponse

data class Earthquake(
    val id: String,
    val title: String? = null,
    val mag: Double? = null,
    val place: String? = null,
    val time: Long? = null,
    val url: String? = null,
    val detail: String? = null,
    val status: String? = null,
    val tsunami: Int? = null,
    val coordinates: Coordinates? = null
)

fun EarthquakeApiResponse.toEarthquakeList(): List<Earthquake>? {
    return features?.map { earthquake ->
        Earthquake(
            earthquake.id,
            earthquake.properties?.title,
            earthquake.properties?.mag,
            earthquake.properties?.place,
            earthquake.properties?.time,
            earthquake.properties?.url,
            earthquake.properties?.detail,
            earthquake.properties?.status,
            earthquake.properties?.tsunami,
            Coordinates(
                earthquake.geometry?.coordinates?.get(1),
                earthquake.geometry?.coordinates?.get(0)
            )
        )
    }
}

fun EarthquakeEntity.toEarthquake() =
    Earthquake(id, title, mag, place, time, url, detail, status, tsunami,
        Coordinates(latitude , longitude)
    )
