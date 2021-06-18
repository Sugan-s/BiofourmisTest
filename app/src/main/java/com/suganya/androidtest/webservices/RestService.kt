package com.suganya.androidtest.webservices


import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestService {

    @get:GET("planetary/apod?api_key=DEMO_KEY")
    val getCurrentDateApod: Call<JsonObject>

    @get:GET("planetary/apod?api_key=DEMO_KEY&start_date=2021-05-20&end_date=2021-06-17")
    val getdateApod: Call<JsonArray>

    @GET("planetary/apod?{api_key}")
    fun getApodForDateRange(@Path("api_key") api_key: String ): Call<JsonArray>
}