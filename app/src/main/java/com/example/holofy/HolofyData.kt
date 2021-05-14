package com.example.holofy

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class HolofyData {
    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null
}