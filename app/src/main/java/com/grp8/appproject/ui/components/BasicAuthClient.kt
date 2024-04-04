package com.grp8.appproject.ui.components

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.runtime.Composable
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.grp8.appproject.models.BasicSignInResult
import com.grp8.appproject.models.BasicSignUpResult
import com.grp8.appproject.models.BasicUser
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class BasicAuthClient() {
    private val auth:FirebaseAuth = Firebase.auth

    suspend fun signUp(email:Email, password:Password): BasicSignUpResult{
        return try {
            val user =
                auth.createUserWithEmailAndPassword(email.value, password.value).await()?.user
                    ?:return BasicSignUpResult(null,"UNKNOWN_ERROR")
            user.sendEmailVerification().await()
            BasicSignUpResult(
                BasicUser(user.uid, user.email?.let { Email(it) }), null
            )
        } catch(e:Exception){
            BasicSignUpResult(null,e.message)
        }
    }

    suspend fun signIn(email:Email, password:Password): BasicSignInResult {
        return try {
            val user = auth.signInWithEmailAndPassword(email.value, password.value).await()?.user
                ?: return BasicSignInResult(null,"UNKNOWN_ERROR")
            BasicSignInResult(BasicUser(user.uid, user.email?.let { Email(it)}),null)
        } catch(e:Exception) {
            if(e is CancellationException) throw e
            if(e is FirebaseAuthException) {
                BasicSignInResult(null,e.message)
            }
            BasicSignInResult(null,e.message)
        }
    }

    fun signOut() {
        auth.signOut()
    }
}