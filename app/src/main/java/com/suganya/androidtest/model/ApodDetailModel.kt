package com.suganya.androidtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApodDetailModel {
    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("explanation")
    @Expose
    var explanation: String? = null

    @SerializedName("hdurl")
    @Expose
    var hdurl: String? = null

    @SerializedName("media_type")
    @Expose
    var media_type: String? = null

    @SerializedName("service_version")
    @Expose
    var service_version: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}