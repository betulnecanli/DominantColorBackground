package com.betulnecanli.dominantcolorbackground.network

import com.betulnecanli.dominantcolorbackground.model.Result
import retrofit2.Response
import retrofit2.http.GET

interface ResultApi {

    @GET("list.json")
    suspend fun getList() : Response<Result>

}