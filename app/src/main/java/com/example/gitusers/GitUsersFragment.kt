package com.example.gitusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.example.gitusers.Model.GitUserDataModeListlItem
import com.example.gitusers.view.userListItem
import com.example.gitusers.viewModel.GitUsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitUsersFragment() : Fragment() {
    private val gitUsersViewModel : GitUsersViewModel by hiltNavGraphViewModels(R.id.my_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.git_users_fragment, container, false)
            .apply {
                findViewById<ComposeView>(R.id.composeView).setContent {
                    Surface(modifier = Modifier.clickable { }) {
                        showGitUsersList(gitUserList = gitUsersViewModel.gitUsersListAPIResponese)
                        gitUsersViewModel.getGitUsersList()
                    }
                }
            }
        return view
    }


    @Composable
    fun showGitUsersList(gitUserList: List<GitUserDataModeListlItem>) {
        var selectedIndex by remember { mutableStateOf(-1) }
        LazyColumn {
            itemsIndexed(items = gitUserList) { index, item ->
                userListItem(gitUserDataModelItem = item, index, selectedIndex) { i ->
                    selectedIndex = i
                    showDetails(selectedIndex)
                }
            }
        }
    }

    private fun showDetails(selectedIndex: Int) {
        val bundle = bundleOf("login" to gitUsersViewModel.gitUsersListAPIResponese[selectedIndex].login)
        findNavController().navigate(R.id.action_gitUsersFragment_to_userDetailsFragment, bundle)
    }
}


