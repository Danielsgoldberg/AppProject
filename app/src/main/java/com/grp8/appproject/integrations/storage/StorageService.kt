package com.grp8.appproject.integrations.storage

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await

object StorageService {
    private val storage = Firebase.storage

    suspend fun UploadeProfileImage(
        user: String,
        localImageUrl: Uri
    ): Uri {
        val storageRef = storage.reference
        val profileImageRef = storageRef.child("profilePictures/$user/ProfileImage")

        val result = profileImageRef.putFile(localImageUrl).await()
        return result.metadata?.reference?.downloadUrl?.await()
            ?: throw Exception("Unknown storage error")
    }
}