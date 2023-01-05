package com.example.androidgithubapi.ui.theme.views.mainView

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidgithubapi.model.UserDetail
import com.example.androidgithubapi.network.ClientService
import com.example.androidgithubapi.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val provideRetrofit: ClientService,
) : ViewModel() {

    var userListResponse: List<UserDetail> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    var isLoading by mutableStateOf(true)

    private fun getUsersList(){
        viewModelScope.launch{
            try {
                when(val usersList = provideRetrofit.getUsers()) {
                    is NetworkResponse.Success -> {
                        isLoading = !isLoading
                        userListResponse = usersList.body
                    }
                    is NetworkResponse.ApiError -> {
                        isLoading = !isLoading
                        errorMessage = "Api Error"
                    }
                    is NetworkResponse.NetworkError -> {
                        isLoading = !isLoading
                        errorMessage = "Network Error"
                    }
                    is NetworkResponse.UnknownError -> {
                        isLoading = !isLoading
                        errorMessage = "Something went wrong"
                    }
                }
            }
            catch (err: Exception) {
                errorMessage = err.message.toString()
            }
        }
    }
    init {
        getUsersList()
    }
}