package com.example.earthquakeinformation.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.earthquakeinformation.data.domain.Earthquake

@Entity(tableName = "earthquake_table")
data class EarthquakeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "mag") val mag: Double?,
    @ColumnInfo(name = "place") val place: String?,
    @ColumnInfo(name = "time") val time: Long?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "detail") val detail: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "tsunami") val tsunami: Int?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "Longitude") val Longitude: Double?
)

fun Earthquake.toEntity() =
    EarthquakeEntity(id, title, mag, place, time, url, detail, status, tsunami, coordinates?.get(0), coordinates?.get(1))
