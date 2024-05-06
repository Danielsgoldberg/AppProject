package com.grp8.appproject.integrations.firestore.model

import android.net.Uri


data class CocktailFS(var id: String = "", val name: String="", val mixer: Array<String>,val instruction: String = "",val spirit: Array<String>, val image: Uri)
{
    fun ToCocktail() : Cocktail
    {
        return Cocktail(id, name, spirit,mixer, instruction ,image)
    }
}

