package com.imaginato.imaginato_practical.data.randomuser.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomUserResponse(
    @SerialName("results")
    val results: ArrayList<RandomUserItem>
)

@Parcelize
@Serializable
data class Location(
    @SerialName("country")
    val country: String,
    @SerialName("city")
    val city: String,
    @SerialName("postcode")
    val postcode: String,
    @SerialName("state")
    val state: String
) : Parcelable

@Parcelize
@Serializable
data class RandomUserItem(
    @SerialName("phone")
    val phone: String,
) : Parcelable {
    @SerialName("nat")
    val nat: String = ""

    @SerialName("gender")
    val gender: String = ""

    @SerialName("dob")
    var dob: Dob? = null

    @SerialName("name")
    var name: Name? = null

    @SerialName("location")
    var location: Location? = null

    @SerialName("cell")
    val cell: String = ""

    @SerialName("email")
    val email: String = ""

    @SerialName("picture")
    var picture: Picture? = null

    var isFavorite: Boolean = false
}

@Parcelize
@Serializable
data class Dob(
    @SerialName("date")
    val date: String,
    @SerialName("age")
    val age: Int
) : Parcelable

@Parcelize
@Serializable
data class Picture(
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("large")
    val large: String,
    @SerialName("medium")
    val medium: String
) : Parcelable

@Parcelize
@Serializable
data class Name(
    @SerialName("last")
    val last: String,
    @SerialName("title")
    val title: String,
    @SerialName("first")
    val first: String
) : Parcelable
