package com.johnny.insytgroove.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PostMDL : RealmObject() {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("userId")
    @Expose
    var userId: Int = 0

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("body")
    @Expose
    var body: String? = null
}