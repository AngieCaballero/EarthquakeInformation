package com.example.earthquakeinformation.data.model

data class EarthquakeApiResponse(
    val type: String? = null,
    val metadata: MetadataModel? = null,
    val features: List<FeatureModel>? = null,
    val bbox: List<Double>? = null
)
