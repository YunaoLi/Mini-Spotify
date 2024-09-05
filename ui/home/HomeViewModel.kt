package com.laioffer.spotify.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laioffer.spotify.datamodel.Section
import com.laioffer.spotify.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// state store
// handle event
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState(true, listOf()))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun fetchHomeScreen(){
        viewModelScope.launch {
            val sections = repository.getHomeSections()
            _uiState.value = HomeUiState(isLoading = false, feed = sections)
            Log.d("HomeViewModel", _uiState.value.toString())
        }
    }
}


data class HomeUiState(
    val isLoading: Boolean,
    val feed: List<Section>
)