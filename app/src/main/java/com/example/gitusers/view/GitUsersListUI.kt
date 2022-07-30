package com.example.gitusers.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.gitusers.Model.GitUserDataModeListlItem
import com.google.gson.Gson


@Composable
fun userListItem(
    gitUserDataModelItem: GitUserDataModeListlItem,
    index: Int,
    selectedIndex: Int,
    onClick: (Int) -> Unit
) {

    Card(
        modifier = Modifier
            .clickable { onClick(index) }
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp

    ) {
        Surface() {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = gitUserDataModelItem.avatar_url,

                        builder = {
                            scale(Scale.FILL)
                            placeholder(coil.compose.base.R.drawable.notification_icon_background)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = gitUserDataModelItem.id.toString(),
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                        .padding(4.dp)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = "${gitUserDataModelItem.id}.  ${gitUserDataModelItem.login}  (${gitUserDataModelItem.type})",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "repo: ${gitUserDataModelItem.repos_url}",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(4.dp)
                    )

                }
            }
        }
    }

}
@Composable
fun Showdetail(index: Int){}


@Preview
@Composable
fun PreviewUserInTheList(){
    //TODO add example model to view the design
    val json = """{}"""
    val gson = Gson()
    val gitUserDataModelItem: GitUserDataModeListlItem = gson.fromJson(json, GitUserDataModeListlItem::class.java)
    var index :Int= 0
    userListItem(gitUserDataModelItem ,index,index){(index)}
}




