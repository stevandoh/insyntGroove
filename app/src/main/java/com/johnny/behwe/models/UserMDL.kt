package com.johnny.behwe.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserMDL: RealmObject() {
    @PrimaryKey
    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("firstname")
    @Expose
    var firstname: String? = null

    @SerializedName("lastname")
    @Expose
    var lastname: String? = null

    @SerializedName("role")
    @Expose
    var role: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    var pin: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("picture")
    @Expose
    var picture: String? = null

    @SerializedName("fullname")
    @Expose
    var fullname: String? = null

    @SerializedName("createdAt")
    @Expose
    var createdAt: Boolean? = null

}