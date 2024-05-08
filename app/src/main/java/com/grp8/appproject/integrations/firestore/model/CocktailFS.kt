package com.grp8.appproject.integrations.firestore.model


data class CocktailFS(
    var id: String = "", val name: String="", val mixer: String,
    val instruction: String = "",
    val spirit: String, val image: String?
)
{
    constructor() : this("", "", "", "", "", null)
    fun ToCocktail() : Cocktail
    {
        return Cocktail(id, name, spirit,mixer, instruction ,image)
    }
}

