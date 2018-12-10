package com.johnny.insytgroove.pojo

import com.google.gson.annotations.SerializedName

open class ServerResponse {

    @SerializedName("success")
    var success: Boolean = false
    @SerializedName("message")
    var message: String? = null


    override fun toString(): String {
        return "ServerResponse{" +
                "success=" + success +
                ", message='" + message + '\''.toString() +
                '}'.toString()
    }
}
