package com.chaimaerazzouki.trendingmovies.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaimaerazzouki.trendingmovies.presentation.util.eventBus.EventBus
import kotlinx.coroutines.launch

fun ViewModel.sendEvent(event : Any) {
    viewModelScope.launch{
        EventBus.sendEvent(event)
    }
}