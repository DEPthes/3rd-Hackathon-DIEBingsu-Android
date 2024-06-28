package com.depth.diebingsu.presentation.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.depth.diebingsu.data.remote.model.Information
import com.depth.diebingsu.data.remote.model.IngredientDTO
import com.depth.diebingsu.domain.repository.Repository
import com.depth.diebingsu.domain.repository.RepositoryImpl
import com.depth.diebingsu.entity.BingsuImage
import com.depth.diebingsu.entity.RandomIngredient
import com.depth.diebingsu.presentation.utils.UiState
import kotlinx.coroutines.launch

class SlotMachineViewModel: ViewModel() {
    private val repositoryImpl = RepositoryImpl()
    private val _ingredients = MutableLiveData<UiState<RandomIngredient>>(UiState.Loading)

    var item1 = ""
    var item2 = ""
    var item3 = ""

    val ingredients get() = _ingredients

    private val _generate = MutableLiveData<UiState<BingsuImage>>(UiState.Loading)
    val generate get() = _generate

    fun getRandomIngredients() {
        _ingredients.value = UiState.Loading

        viewModelScope.launch {
            repositoryImpl.getRandomIngredients()
                .onSuccess { _ingredients.value = UiState.Success(it) }
                .onFailure {
                    it.printStackTrace()
                    _ingredients.value = UiState.Failure(400, it.message.toString())
                }
        }
    }

    fun getGenerate(information: Information) {
        _generate.value = UiState.Loading

        viewModelScope.launch {
            repositoryImpl.getGenerate(information)
                .onSuccess { _generate.value = UiState.Success(it) }
                .onFailure {
                    it.printStackTrace()
                    _generate.value = UiState.Failure(400, it.message.toString())
                }
        }
    }
}