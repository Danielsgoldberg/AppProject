package com.grp8.appproject.ui.components.api

import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
data class Drink(

    @SerialName("strIngredient1") var strIngredient1: String? = null

)