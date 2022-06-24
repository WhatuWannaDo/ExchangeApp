package com.example.exchangeapp.data.Network

import com.example.exchangeapp.data.Models.CurrenciesModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrenciesAPI {

    @Headers("apiKey: EiohSBuHx82h3TRN6HEwA8upBs512fWy")
    @GET("latest")
    suspend fun getCurrencies(
        @Query("base") base : String
    ) : Response<CurrenciesModelResponse>

}