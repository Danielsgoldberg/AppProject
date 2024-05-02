package com.grp8.appproject.ui.components.api

import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
data class Drinks (

  @SerialName("drinks" ) var drinks : List<Drink> = emptyList()

)

