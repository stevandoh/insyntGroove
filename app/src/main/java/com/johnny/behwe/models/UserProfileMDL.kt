package com.johnny.behwe.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserProfileMDL : RealmObject() {
    @PrimaryKey
    @SerializedName("token")
    var token: String? = null
    @SerializedName("agent")
    var user: UserMDL? = null

}