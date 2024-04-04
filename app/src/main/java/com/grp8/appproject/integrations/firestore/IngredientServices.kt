package com.grp8.appproject.integrations.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.grp8.appproject.integrations.firestore.model.CocktailFS
import com.grp8.appproject.integrations.firestore.model.IngredientFS
import kotlinx.coroutines.tasks.await

class IngredientServices {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()


    suspend fun createIngredient(ingredient: IngredientFS) {
        db.collection("Ingredients").add(ingredient).await()
    }
    suspend fun getIngredients(): List<IngredientFS> {
        val ingredients = db.collection("Ingredients").get().await()
        return ingredients.documents.mapNotNull { document ->
            val ingredient = document.toObject<IngredientFS>()
            ingredient?.id = document.id // Assign the document ID to a property in the data class
            ingredient // Return the deserialized object with the document ID included
        }
    }

    suspend fun getIngredient(id: String): IngredientFS? {
        val ingredient = db.collection("Ingredients").document(id).get().await()
        if (ingredient.exists()) {
            return ingredient.toObject<IngredientFS>()
        }
        return null
    }

}