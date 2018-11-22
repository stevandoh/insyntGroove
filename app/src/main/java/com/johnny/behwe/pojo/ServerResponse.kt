

import com.google.gson.annotations.SerializedName
open class ServerResponse {

    @SerializedName("success")
    var success: Boolean = false
    @SerializedName("message")
    var message: String? = null
    @SerializedName("user")
    var user: User? = null
    @SerializedName("token")
    var token: String? = null

    override fun toString(): String {
        return "ServerResponse{" +
                "success=" + success +
                ", message='" + message + '\''.toString() +
                ", user=" + user +
                ", token='" + token + '\''.toString() +
                '}'.toString()
    }
}
