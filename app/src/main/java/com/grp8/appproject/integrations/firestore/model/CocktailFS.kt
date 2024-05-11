package com.grp8.appproject.integrations.firestore.model


data class CocktailFS(
    var id: String = "", val name: String = "", val mixer: String,
    val instruction: String = "",
    val spirit: String,
    val image: String?,
    val spiritAmount: Int,
    val mixerAmount: Int
) {
    constructor() : this("", "", "", "", "", null, 0,0)

    fun toCocktail(): Cocktail {
        return Cocktail(id, name, spirit, mixer, instruction, image, spiritAmount, mixerAmount)
    }
}

