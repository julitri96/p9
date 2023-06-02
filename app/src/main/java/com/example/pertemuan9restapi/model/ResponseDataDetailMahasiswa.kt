package com.example.pertemuan9restapi.model


import com.google.gson.annotations.SerializedName

data class ResponseDataDetailMahasiswa(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: String
)