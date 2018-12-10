package com.johnny.insytgroove.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserMDL: RealmObject() {


    @PrimaryKey
    @SerializedName("id")
    @Expose
   var id: Int = 0
    @SerializedName("name")
    @Expose
   var name: String? = null
    @SerializedName("username")
    @Expose
   var username: String? = null
    @SerializedName("email")
    @Expose
   var email: String? = null
    @SerializedName("phone")
    @Expose
   var phone: String? = null
    @SerializedName("website")
    @Expose
   var website: String? = null

}