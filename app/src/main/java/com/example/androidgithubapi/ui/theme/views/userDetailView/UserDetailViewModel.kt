package com.example.androidgithubapi.ui.theme.views.userDetailView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidgithubapi.model.UserRepository
import com.example.androidgithubapi.network.ClientService
import com.example.androidgithubapi.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val provideRetrofit: ClientService,
) : ViewModel() {

    var reposListResponse:List<UserRepository> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

     fun getReposList(
        argument: String
    ) : List<UserRepository>{
        viewModelScope.launch{
            try {
                when(val reposList = provideRetrofit.getRepos(login = argument)) {
                    is NetworkResponse.Success -> {
                        reposListResponse = reposList.body
                    }
                    is NetworkResponse.ApiError -> {
                        errorMessage = "Api Error"
                    }
                    is NetworkResponse.NetworkError -> {
                        errorMessage = "Network Error"
                    }
                    is NetworkResponse.UnknownError -> {
                        errorMessage = "Something went wrong"
                    }
                }
                return@launch
            }
            catch (err: Exception) {
                errorMessage = err.message.toString()
                return@launch
            }
        }

         return reposListResponse
    }
}

