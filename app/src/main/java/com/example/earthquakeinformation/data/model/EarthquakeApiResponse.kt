package com.example.earthquakeinformation.data.model

data class EarthquakeApiResponse(
    val type: String? = null,
    val metadata: MetadataModel? = null,
    val feature: List<FeatureModel>? = null,
    val bbox: List<Double>? = null
)
