package com.johnny.behwe.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class User {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("lastname")
    @Expose
    var lastname: String? = null
    @SerializedName("firstname")
    @Expose
    var firstname: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("phone")
    @Expose
    var phone: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("lmcId")
    @Expose
    var lmcId: String? = null
    @SerializedName("terminalId")
    @Expose
    var terminalId: String? = null
    @SerializedName("__v")
    @Expose
    var v: Int = 0
    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String? = null
    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("status")
    @Expose
    var status: Int = 0
    @SerializedName("fullname")
    @Expose
    var fullname: String? = null
    @SerializedName("role")
    @Expose
    var role: String? = null
    @SerializedName("active")
    @Expose
    var active: Boolean? = null


}