package com.grp8.appproject.integrations.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.grp8.appproject.integrations.firestore.model.Cocktail
import com.grp8.appproject.integrations.firestore.model.CocktailFS
import kotlinx.coroutines.tasks.await

class CocktailServices {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()




        suspend fun getCocktails(): List<CocktailFS> {
            val cocktails = db.collection("Cocktails").get().await()
            return cocktails.documents.mapNotNull { document ->
                val cocktail = document.toObject<CocktailFS>()
                cocktail?.id = document.id // Assign the document ID to a property in the data class
                cocktail // Return the deserialized object with the document ID included
            }
        }

        suspend fun getCocktail(id: String): Cocktail? {
            val cocktailRef = db.collection("Cocktails").document(id)
            val cocktailFS = cocktailRef.get().await().toObject<CocktailFS>()
            cocktailFS?.id =  cocktailRef.id
            return cocktailFS?.toCocktail()
        }


    }
