package com.example.gitusers.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.gitusers.Model.GitUserDetailsModel
import com.google.gson.Gson

@Composable
fun userDetails(
    gitUserDetailsModel: GitUserDetailsModel
){

    Scaffold() {
        Column() {


            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Surface(
                        modifier = Modifier

                            .size(80.dp), shape = CircleShape, elevation = 4.dp
                    ) {

                        Image(


                            painter = rememberImagePainter(
                                data = gitUserDetailsModel.avatar_url,

                                builder = {
                                    scale(Scale.FILL)
                                    placeholder(R.drawable.notification_icon_background)
                                    transformations(CircleCropTransformation())

                                }
                            ),
                            contentDescription = gitUserDetailsModel.id.toString(),
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.2f)
                                .padding(4.dp)
                                .fillMaxWidth(),


                            contentScale = ContentScale.FillBounds

                        )
                    }

                    Spacer(modifier = Modifier.width(30.dp))
                    BuildDashboard(gitUserDetailsModel.followers.toString(), "Posts")

                    Spacer(modifier = Modifier.width(30.dp))
                    BuildDashboard(gitUserDetailsModel.followers.toString(), "Followers")
                    Spacer(modifier = Modifier.width(30.dp))
                    BuildDashboard(gitUserDetailsModel.following.toString(), "following")
                }
                Text(text = gitUserDetailsModel.bio.toString())


            }
            //PostTabView(userData.imagePost)
        }
    }




}




@Composable
fun BuildDashboard(count: String, name: String) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = name, fontWeight = FontWeight.Normal)
    }
}

@Preview
@Composable
fun PreviewUserDetails(){
    //TODO add example model to view the design
    val json = """{
  "login": "brynary",
  "id": 19,
  "node_id": "MDQ6VXNlcjE5",
  "avatar_url": "https://avatars.githubusercontent.com/u/19?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/brynary",
  "html_url": "https://github.com/brynary",
  "followers_url": "https://api.github.com/users/brynary/followers",
  "following_url": "https://api.github.com/users/brynary/following{/other_user}",
  "gists_url": "https://api.github.com/users/brynary/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/brynary/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/brynary/subscriptions",
  "organizations_url": "https://api.github.com/users/brynary/orgs",
  "repos_url": "https://api.github.com/users/brynary/repos",
  "events_url": "https://api.github.com/users/brynary/events{/privacy}",
  "received_events_url": "https://api.github.com/users/brynary/received_events",
  "type": "User",
  "site_admin": false,
  "name": "Bryan Helmkamp",
  "company": "Code Climate",
  "blog": "http://codeclimate.com",
  "location": "New York City",
  "email": null,
  "hireable": null,
  "bio": "Co-founder and CEO, Code Climate",
  "twitter_username": null,
  "public_repos": 165,
  "public_gists": 68,
  "followers": 647,
  "following": 27,
  "created_at": "2008-01-13T10:19:47Z",
  "updated_at": "2022-07-26T16:45:52Z"
}"""
    val gson = Gson()
    val gitUserDetailsModel: GitUserDetailsModel = gson.fromJson(json, GitUserDetailsModel::class.java)
    userDetails(gitUserDetailsModel)
}
