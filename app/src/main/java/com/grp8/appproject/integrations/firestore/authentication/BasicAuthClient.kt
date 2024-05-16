package com.grp8.appproject.integrations.firestore.authentication

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.grp8.appproject.dataservice.UserService
import com.grp8.appproject.models.BasicSignInResult
import com.grp8.appproject.models.BasicSignUpResult
import com.grp8.appproject.models.BasicUser
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class BasicAuthClient() {
    private val auth: FirebaseAuth = Firebase.auth

    suspend fun signUp(email: String, password: String): BasicSignUpResult {
        return try {
            val user =
                auth.createUserWithEmailAndPassword(email.toString(), password).await()?.user
                    ?: return BasicSignUpResult(null, "UNKNOWN_ERROR")
            user.sendEmailVerification().await()

            UserService.createUser(user.uid, user.email!!)
            val basicUser=UserService.GetUser(user.uid)
            BasicSignUpResult(
                basicUser, null
            )
        } catch (e: Exception) {
            BasicSignUpResult(null, e.message)
        }
    }

    suspend fun signIn(email: String, password: String): BasicSignInResult {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await()?.user
                ?: return BasicSignInResult(null, "UNKNOWN_ERROR")


            val userFS = FireStoreService.getUser(user.uid)
            BasicSignInResult(
                BasicUser(
                    user.uid,
                    user.email ?: "",
                    displayName = userFS?.name ?: "",
                    profileIcon = userFS?.let{ Uri.parse(it.profileurl)}?: Uri.EMPTY
                ),
                null
            )
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            if (e is FirebaseAuthException) {
                BasicSignInResult(null, e.message)
            }
            BasicSignInResult(null, e.message)
        }
    }

    fun signOut() {
        auth.signOut()
    }
}