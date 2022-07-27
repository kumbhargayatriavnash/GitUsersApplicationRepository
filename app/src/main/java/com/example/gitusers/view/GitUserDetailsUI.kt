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
import androidx.compose.ui.unit.dp
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.gitusers.Model.GitUserDetailsModel

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

