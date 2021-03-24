package com.mobius.demoapp.response

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class APIResponse {

@SerializedName("id")
var id: String? = null

@SerializedName("valid_from")
var valid_from: String? = null


@SerializedName("valid_until")
var valid_until: String? = null

@SerializedName("is_active")
var is_active: Boolean? = null

@SerializedName("is_deleted")
var is_deleted: Boolean? = null

@SerializedName("created_at")
var created_at: String? = null


@SerializedName("code")
var code: String? = null

@SerializedName("ribbon_msg")
var ribbon_msg: String? = null

@SerializedName("wager_to_release_ratio_numerator")
var wager_to_release_ratio_numerator: Int = 0

@SerializedName("wager_to_release_ratio_denominator")
var wager_to_release_ratio_denominator: Int = 0

@SerializedName("wager_bonus_expiry")
var wager_bonus_expiry: Int = 0


@SerializedName("slabs")
var slabs = ArrayList<Slab>()

}

class Slab {

    @SerializedName("name")
    var main: String? = null


    @SerializedName("min")
    var min: Int = 0

    @SerializedName("max")
    var max: Int = 0

    @SerializedName("wagered_percent")
    var wagered_percent: Int = 0

    @SerializedName("wagered_max")
    var wagered_max: Int = 0

    @SerializedName("otc_percent")
    var otc_percent: Int = 0

    @SerializedName("otc_max")
    var otc_max: Int = 0


}
