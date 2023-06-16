package com.capstone.swalokal.data.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PredictionResponse(

    @field:SerializedName("detail")
    val detail: Detail,
)

@Parcelize
data class PredictItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("price")
    val price: Int,

    @field:SerializedName("toko")
    val toko: String,

    @field:SerializedName("latitude")
    val latitude: Double,

    @field:SerializedName("longitude")
    val longitude: Double,

    @field:SerializedName("url")
    val url: String,

) : Parcelable

data class Detail(

    @field:SerializedName("eror")
    val eror: Boolean,

    @field:SerializedName("data")
    val data: List<PredictItem>,
)
