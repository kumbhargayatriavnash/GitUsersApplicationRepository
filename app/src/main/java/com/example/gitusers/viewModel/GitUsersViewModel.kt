package com.example.gitusers.viewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitusers.Model.GitUserDataModeListlItem
import com.example.gitusers.network.GitApiService
import kotlinx.coroutines.launch

class GitUsersViewModel : ViewModel() {
    companion object {
        private const val TAG = "GitUsersViewModel"
    }

    var gitUsersListAPIResponese: List<GitUserDataModeListlItem> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getGitUsersList() {
        viewModelScope.launch {
            val apiService = GitApiService.getInstance()
            try {
                val response = apiService.getGitUsers()
                Log.d(
                    Companion.TAG,
                    "getGitUsersList Response :: $response.code() : ${response.message()}}"
                )
                if (response.isSuccessful)//code 200 OK
                    gitUsersListAPIResponese = response.body() ?: emptyList();
                else //304 not modified
                    Log.e(
                        Companion.TAG,
                        "getGitUsersList: ERROR :: $response.code() : ${response.message()}}"
                    )
                //TODO show error dialog when response not success
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e(Companion.TAG, "getGitUsersList: ERROR :: $errorMessage")
            }
        }
    }
}



