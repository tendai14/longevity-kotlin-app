package com.example.androidgithubapi.ui.theme.views.userDetailView

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import com.example.androidgithubapi.model.UserRepository

@Composable
fun UserDetailsScreen(
    navController: NavHostController,
    viewModel: UserDetailViewModel = hiltViewModel(),
    argument: String?,
) {
    val repos = viewModel.getReposList(argument!!)

    if (repos.isNotEmpty()) {
        LazyColumn(modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp)) {
            repos.forEach { repo ->
                item {
                    RepositoryItem(
                        name = repo.name, updated_at = repo.updated_at,
                        stargazers_count = repo.stargazers_count, language = repo.language,
                        url = repo.html_url
                    )
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
fun RepositoryItem(
    name: String,
    updated_at: String,
    stargazers_count: Int,
    language: String?,
    url: String,
){
    val lang = language ?: "N/A"
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable {
                uriHandler.openUri(url)
            },
        elevation = 10.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Column() {
                Text(text = "Repository Name: $name")
                Spacer(modifier = Modifier.padding(vertical = 3.dp))
                Text(text = "Updated At: $updated_at")
                Spacer(modifier = Modifier.padding(vertical = 3.dp))
                Text(text = "Stargazers Count: $stargazers_count")
                Spacer(modifier = Modifier.padding(vertical = 3.dp))
                Text(text = "Language: $lang")
                Spacer(modifier = Modifier.padding(vertical = 3.dp))
            }
        }
    }

}