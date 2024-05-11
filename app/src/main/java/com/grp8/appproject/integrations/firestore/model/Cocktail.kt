package com.grp8.appproject.integrations.firestore.model


data class Cocktail(
    val id: String,
    val name: String, val spirit: String, val mixer: String,
    val instruction: String,
    val image: String?,
    val spiritAmount: Int,
    val mixerAmount: Int


)
