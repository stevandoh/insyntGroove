package com.johnny.insytgroove.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CommentMDL : RealmObject() {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("postId")
    @Expose
    var postId: Int = 0

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("body")
    @Expose
    var body: String? = null

}
