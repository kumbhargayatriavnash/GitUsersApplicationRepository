package com.example.gitusers.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.gitusers.model.GitUserDetailsModel
import com.google.gson.Gson

@Composable
fun userDetails(
    gitUserDetailsModel: GitUserDetailsModel
) {
    MaterialTheme {

        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), shape = RoundedCornerShape(8.dp), elevation = 4.dp

        ) {
            Surface() {
                Column(
                    Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                ) {

                    Row(
                        Modifier
                            .padding(4.dp)
                            .height(110.dp)


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
                            contentDescription = gitUserDetailsModel.name.toString(),
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.2f)
                                .padding(4.dp)
                        )

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(0.8f)
                        ) {
                            Text(
                                text = "  ${gitUserDetailsModel.name}",
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "  ${gitUserDetailsModel.login}",
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Normal
                            )
                            Row() {
                                Icon(Icons.Rounded.LocationOn, contentDescription = "Localized description")

                                Text(
                                    text = "  ${gitUserDetailsModel.location}",
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Normal
                                )
                            }


                        }
                    }

                    Row() {
                        //Icon(Icons.Rounded.Person, contentDescription = "Localized description")
                        Text(
                            text = "  ${gitUserDetailsModel.followers} Followers . ${gitUserDetailsModel.following} Following",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Row() {
                        Icon(Icons.Rounded.Info, contentDescription = "Localized description")

                        Text(
                            text = "  ${gitUserDetailsModel.bio}",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Normal
                        )
                    }



                    Row() {
                        Icon(Icons.Rounded.Email, contentDescription = "Localized description")

                        Text(
                            text = "  Email: ${gitUserDetailsModel.email}",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Normal
                        )
                    }





                }
            }


        }
    }
}


@Preview
@Composable
fun PreviewUserDetails() {
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
    val gitUserDetailsModel: GitUserDetailsModel =
        gson.fromJson(json, GitUserDetailsModel::class.java)
    userDetails(gitUserDetailsModel)
}
