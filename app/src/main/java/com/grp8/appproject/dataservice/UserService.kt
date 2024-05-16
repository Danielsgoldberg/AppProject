package com.grp8.appproject.dataservice

import android.net.Uri
import com.grp8.appproject.integrations.firestore.authentication.FireStoreService
import com.grp8.appproject.integrations.storage.StorageService
import com.grp8.appproject.models.BasicUser

object UserService {
    suspend fun UploadProfileImage(uri: Uri, id: String){
        val storageUri = StorageService.UploadeProfileImage(id,uri)
        FireStoreService.UpdateProfilImage(storageUri, id)
    }

    suspend fun GetUser(id:String):BasicUser?{
        return FireStoreService.getBasicUser(id)
    }

    suspend fun createUser(id:String, email:String){
        FireStoreService.createUser(id, email)
    }
}