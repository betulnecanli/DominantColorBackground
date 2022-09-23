package com.betulnecanli.dominantcolorbackground.ui

import androidx.lifecycle.ViewModel
import com.betulnecanli.dominantcolorbackground.network.ResultApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    val api : ResultApi ) : ViewModel(){




}