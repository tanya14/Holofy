package com.example.holofy

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Video {
    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("sources")
    @Expose
    var sources: ArrayList<String>? = ArrayList()

    @SerializedName("subtitle")
    @Expose
    var subtitle: String? = null

    @SerializedName("thumb")
    @Expose
    var thumb: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null
}