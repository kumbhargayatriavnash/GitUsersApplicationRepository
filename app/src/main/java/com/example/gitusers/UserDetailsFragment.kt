package com.example.gitusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gitusers.model.GitUserDetailsModel
import com.example.gitusers.view.userDetails
import com.example.gitusers.viewModel.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var userDetailsViewModel: UserDetailsViewModel
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
                        userDetailsViewModel.getGitUsersDetails(login)
                    }
                }
            }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userDetailsViewModel = ViewModelProvider(this).get(UserDetailsViewModel::class.java)

        login = arguments?.getString("login").toString()
        Toast.makeText(context, "clicked $login", Toast.LENGTH_SHORT).show()
        userDetailsViewModel.getGitUsersDetails(login)

    }

    @Composable
    fun showGitUsersDetails(gitUserDetailsModel: GitUserDetailsModel) {
            userDetails(gitUserDetailsModel)
        }
}