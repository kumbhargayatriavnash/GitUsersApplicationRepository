package com.example.gitusers.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitusers.Model.GitUserDataModeListlItem
import com.example.gitusers.network.GitApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitUsersViewModel @Inject constructor(): ViewModel() {
    companion object {
        private const val TAG = "GitUsersViewModel"
    }

    var gitUsersListAPIResponese: List<GitUserDataModeListlItem> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    private val apiService = GitApiService.getInstance()

    fun getGitUsersList() {
        viewModelScope.launch {

            try {
                val response = apiService.getGitUsers()
                Log.d(Companion.TAG, "getGitUsersList Response :: $response.code() : ${response.message()}}")
                if (response.isSuccessful)//code 200 OK
                    gitUsersListAPIResponese = response.body() ?: emptyList();
                else //304 not modified
                    Log.e(Companion.TAG, "getGitUsersList: ERROR :: $response.code() : ${response.message()}}")
                //TODO show error dialog when response not success
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e(Companion.TAG, "getGitUsersList: ERROR :: $errorMessage")
            }
        }
    }
}



