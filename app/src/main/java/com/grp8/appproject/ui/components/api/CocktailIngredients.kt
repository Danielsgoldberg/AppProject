package com.grp8.appproject.ui.components.api

import kotlinx.serialization.SerialName


data class CocktailIngredients (

  @SerialName("drinks" ) var drinks : ArrayList<Drinks> = arrayListOf()

)