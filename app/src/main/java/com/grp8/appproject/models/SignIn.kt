package com.grp8.appproject.models

import android.net.Uri

data class BasicUser(
    val userId: String,
    val email: String,
    val displayName: String = "",
    val profileIcon: Uri = Uri.EMPTY
)

data class BasicSignInResult(
    val data: BasicUser?,
    val error: String?
)

data class BasicSignUpResult(
    val data: BasicUser?,
    val error: String?
)

