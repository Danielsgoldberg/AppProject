package com.grp8.appproject.integrations.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.grp8.appproject.integrations.firestore.model.CocktailCreateFS
import com.grp8.appproject.integrations.firestore.model.CocktailFS
import com.grp8.appproject.integrations.firestore.model.IngredientFS
import kotlinx.coroutines.tasks.await

class CocktailServices {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()


        suspend fun createCocktail(cocktail: CocktailCreateFS) {
            db.collection("Cocktails").add(cocktail).await()
        }

        suspend fun getCocktails(): List<CocktailFS> {
            val cocktails = db.collection("Cocktails").get().await()
            return cocktails.documents.mapNotNull { document ->
                val cocktail = document.toObject<CocktailFS>()
                cocktail?.id = document.id // Assign the document ID to a property in the data class
                cocktail // Return the deserialized object with the document ID included
            }
        }

        suspend fun getCocktail(id: String): CocktailFS? {
            val cocktail = db.collection("Cocktails").document(id).get().await()
            if (cocktail.exists()) {
                return cocktail.toObject<CocktailFS>()
            }
            return null
        }

    }
