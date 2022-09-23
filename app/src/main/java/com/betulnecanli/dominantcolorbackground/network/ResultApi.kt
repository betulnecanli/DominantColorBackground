package com.betulnecanli.dominantcolorbackground.network

import com.betulnecanli.dominantcolorbackground.model.result
import retrofit2.Response
import retrofit2.http.GET

interface ResultApi {

    @GET
    suspend fun getList() : result

}