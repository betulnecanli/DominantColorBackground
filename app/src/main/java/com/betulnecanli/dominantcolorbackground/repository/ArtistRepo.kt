package com.betulnecanli.dominantcolorbackground.repository

import com.betulnecanli.dominantcolorbackground.network.ResultApi
import javax.inject.Inject

class ArtistRepo @Inject constructor(val api : ResultApi){


    suspend fun getList() = api.getList()
}