package com.depth.diebingsu.presentation.view.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.depth.diebingsu.data.dto.Information
import com.depth.diebingsu.data.repository.BoardRepositoryImpl
import com.depth.diebingsu.presentation.utils.UiState
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val boardRepositoryImpl = BoardRepositoryImpl()
    private val _boardState = MutableLiveData<UiState<List<Information>>>(UiState.Loading)
    val boardState get() = _boardState

    fun getBoard(deviceId: String) {
        _boardState.value = UiState.Loading

        viewModelScope.launch {
            try {
                boardRepositoryImpl.getBoard(deviceId).onSuccess {
                    _boardState.value = UiState.Success(it)
                }.onFailure {
                    _boardState.value = UiState.Failure(0, it.message.toString())
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
                _boardState.value = UiState.Failure(0, e.message.toString())
            }
        }
    }
}