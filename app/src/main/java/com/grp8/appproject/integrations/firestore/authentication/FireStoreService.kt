package com.grp8.appproject.integrations.firestore.authentication

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.grp8.appproject.integrations.firestore.model.UserFS
import com.grp8.appproject.models.BasicUser
import kotlinx.coroutines.tasks.await

object FireStoreService {

    private val auth: FirebaseAuth = Firebase.auth
    suspend fun getUser(id: String): UserFS? {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val ref = db.collection("User").document(id)
        val user = ref.get().await().toObject<UserFS>()
        user?.id = ref.id
        return user;
    }

    suspend fun createUser(id:String, email:String) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("User").document(id).set(mapOf("email" to email)).await()
    }
    suspend fun getBasicUser(id: String): BasicUser? {
        val user=getUser(id)
        if(user==null){
            return null
        }
        return BasicUser(
            userId=user.id!!,
            email=user.email!!,
            displayName=user.name?:"",
            profileIcon = parseUrl(user.profileurl))
    }



    suspend fun UpdateProfilImage(uri: Uri, id: String){
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("User").document(id).update(mapOf("profileurl" to uri.toString())).await()
    }

    suspend fun UpdateName(name: String, id: String){
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("User").document(id).update(mapOf("name" to name)).await()
    }

    private fun parseUrl(url:String?):Uri{
        if(!url.isNullOrEmpty()){
            return Uri.parse(url)
        }
        return Uri.EMPTY
    }

}