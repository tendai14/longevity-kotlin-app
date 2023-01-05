package com.example.androidgithubapi.ui.theme.views.mainView

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.androidgithubapi.model.UserDetail
import com.example.androidgithubapi.navigation.Route
import kotlinx.coroutines.runBlocking

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val users = viewModel.userListResponse
    if (users.isNotEmpty()) {
        LazyColumn(modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp)) {
            users.forEach {
                item {
                    UserItem(avatar = it.avatar_url, login = it.login, navController = navController)
                }
            }
        }
    }
    else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator()
            Text(text = "Fetching Data")
        }
    }
}


@Composable
fun UserItem(
    avatar: String,
    login: String,
    navController: NavHostController,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable {
                navController.navigate("repos/${login}")
            },
        elevation = 10.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(avatar),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(1.dp, Color.Gray, CircleShape)   // add a border (optional)
            )
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            Column() {
                Text(text = login)
            }
        }
    }

}