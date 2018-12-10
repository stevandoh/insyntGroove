package com.dmslgh.android.tswabidi.listItems

import com.dmslgh.android.tswabidi.models.TransactionMDL
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList

open class UserStakeList{
    @SerializedName("docs")
    @Expose
    var stakeList: RealmList<TransactionMDL>? = null
}
