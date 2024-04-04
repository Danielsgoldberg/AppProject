package com.grp8.appproject.models

import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Email

data class BasicUser(
    val userId: String,
    val email: Email?,
    val displayName: String="",
    val profileIcon: Uri= Uri.EMPTY
)
data class BasicSignInResult(
    val data: BasicUser?,
    val error: String?
)

data class BasicSignUpResult(
    val data: BasicUser?,
    val error: String?
)

