package com.mobius.demoapp.retrofit

import com.mobius.demoapp.response.APIResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("v3/4c663239-03af-49b5-bcb3-0b0c41565bd2")
    fun getCurrentWeatherData(): Call<List<APIResponse>>
}