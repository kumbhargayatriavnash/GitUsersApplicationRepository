package com.example.gitusers.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitusers.model.GitUserDetailsModel
import com.example.gitusers.network.GitApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(): ViewModel() {
    companion object {
        private const val TAG = "GitUsersViewModel"
    }
     /// instances need to replaced by dagger dependancy
    val gitApiService = GitApiService.getInstance()
    var gitUserDetailsModelAPIResponse: GitUserDetailsModel by mutableStateOf(GitUserDetailsModel())
    var errorMessage: String by mutableStateOf("")

    fun getGitUsersDetails(login: String) {
        viewModelScope.launch {
            try {
                val response = gitApiService.getGitUserDetails(login)
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