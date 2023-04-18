package com.example.earthquakeinformation.data.model

data class FeatureModel(
    val id: String,
    val type: String? = null,
    val properties: EarthquakePropieties? = null,
    val geometry: EarthquakeGeometry? = null
)
