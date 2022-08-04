package com.example.gitusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gitusers.model.GitUserDetailsModel
import com.example.gitusers.view.userDetails
import com.example.gitusers.viewModel.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private val userDetailsViewModel: UserDetailsViewModel by viewModels()
    private lateinit var login: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.user_details_fragment, container, false)
            .apply {
                findViewById<ComposeView>(R.id.detailsComposeView).setContent {
                    Surface() {
                        showGitUsersDetails(gitUserDetailsModel = userDetailsViewModel.gitUserDetailsModelAPIResponse)
                        login = arguments?.getString("login").toString()
                        userDetailsViewModel.getGitUsersDetails(login)
                    }
                }
            }

        return view
    }


    @Composable
    fun showGitUsersDetails(gitUserDetailsModel: GitUserDetailsModel) {
        userDetails(gitUserDetailsModel)
    }
}