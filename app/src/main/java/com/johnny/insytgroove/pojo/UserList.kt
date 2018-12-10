package com.johnny.insytgroove.pojo


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.johnny.insytgroove.models.UserMDL
import io.realm.RealmList

open class UserList{
    @Expose
    var userList: RealmList<UserMDL>? = null
}
