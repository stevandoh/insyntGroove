package com.johnny.behwe.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class AdMDL : RealmObject(){
    @PrimaryKey
    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("media")
    @Expose
    var media: String? = null

    var mediaPath: String? = null

    var createdDate: String? = null

    var modifiedDate : String? = null

}