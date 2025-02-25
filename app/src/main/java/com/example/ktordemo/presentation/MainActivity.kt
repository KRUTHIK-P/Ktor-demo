package com.example.ktordemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ktordemo.data.model.Post
import com.example.ktordemo.presentation.theme.KTorDemoTheme
import com.example.ktordemo.presentation.viewmodel.ViewModel
import com.example.ktordemo.utils.NetworkResult

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KTorDemoTheme {
                val viewModel = ViewModel()
                MainContent(viewModel)
            }
        }
    }
}

@Composable
fun MainContent(viewModel: ViewModel) {
    val posts by viewModel.post.collectAsState()
    when (posts) {
        is NetworkResult.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .size(50.dp)
            ) {
                CircularProgressIndicator()
            }
        }

        is NetworkResult.Error -> Text(
            text = (posts as? NetworkResult.Error)?.message ?: "An error occurred"
        )

        is NetworkResult.Success -> LazyColumn {
            (posts as? NetworkResult.Success<List<Post>>)?.data?.let { post ->
                items(post) {
                    Text(text = it.title)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = it.body)
                }
            }
        }
    }
}
