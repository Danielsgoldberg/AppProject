package com.grp8.appproject.integrations.firestore.storage

import android.net.Uri
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object StorageService {
    private val storage = Firebase.storage

    suspend fun getUserFile(
        userId: String?,
        path: String,
        fileName: String,
    ): Uri {
        if (userId == null) {
            return Uri.EMPTY
        }
        return suspendCoroutine { continuation ->

            val storageRef = storage.reference
            val profileImageRef = storageRef.child("users/$userId/$path/$fileName")

            profileImageRef.downloadUrl
                .addOnSuccessListener {
                    continuation.resume(it ?: Uri.EMPTY)
                }
                .addOnFailureListener {
                    Log.e(this::class.qualifiedName, "Failed to Obtain profile image")
                    continuation.resumeWithException(it)
                }
        }
    }

    suspend fun uploadUserFile(
        userId: String?,
        path: String,
        fileName: String,
        localImageUri: Uri
    ): Uri {
        if (userId == null) {
            return Uri.EMPTY
        }
        return suspendCoroutine { continuation ->

            val storageRef = storage.reference
            val profileImageRef = storageRef.child("users/$userId/$path/$fileName")
            val uploadTask = profileImageRef.putFile(localImageUri)
            uploadTask.addOnCompleteListener {
                if (it.isSuccessful) {
                    val test = it.result.metadata?.reference?.downloadUrl
                        ?: throw Exception("Meta data failed")
                    test.addOnCompleteListener {
                        continuation.resume(test.result)
                    }.addOnFailureListener {
                        Log.e(this::class.qualifiedName, "Failed To get meta data")
                        throw Exception("Failed upload")
                    }
                }
            }.addOnFailureListener {
                Log.e(this::class.qualifiedName, "Failed upload")
                throw it
            }
        }
    }

}
