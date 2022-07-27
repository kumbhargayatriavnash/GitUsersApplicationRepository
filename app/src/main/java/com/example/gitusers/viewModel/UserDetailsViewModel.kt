package com.example.gitusers.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitusers.Model.GitUserDetailsModel
import com.example.gitusers.network.GitApiService
import kotlinx.coroutines.launch

class UserDetailsViewModel : ViewModel() {
    companion object {
        private const val TAG = "GitUsersViewModel"
    }

    var gitUserDetailsModelAPIResponse: GitUserDetailsModel by mutableStateOf(GitUserDetailsModel())
    var errorMessage: String by mutableStateOf("")

    fun getGitUsersDetails(login: String) {
        viewModelScope.launch {
            val apiService = GitApiService.getInstance()
            try {
                val response = apiService.getGitUserDetails(login)
                Log.d(TAG, "getGitUsersDetails Response :: $response.code() : $response.message()}")
                if (response.isSuccessful)//200 OK
                    gitUserDetailsModelAPIResponse = response.body() ?: GitUserDetailsModel();
                else //404 Resource not found
                    Log.e(
                        TAG,
                        "getGitUsersDetails: ERROR :: $response.code() : ${response.message()}}"
                    )
                //TODO show error dialog when response not success
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e(TAG, "getGitUsersDetails: ERROR :: $errorMessage")
            }
        }
    }
}