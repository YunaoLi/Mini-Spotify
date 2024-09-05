package com.laioffer.spotify

import android.net.Network
import android.os.Bundle
import android.provider.MediaStore.Audio.AlbumColumns
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.laioffer.spotify.ui.theme.SpotifyTheme
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laioffer.spotify.network.NetworkApi
import com.laioffer.spotify.network.NetworkModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


// customized extend AppCompatActivity

// AndroidEntryPoint helps on field inject on Android component
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var networkApi: NetworkApi

    private val TAG = "lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        val navHostFragment =supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController


        navController.setGraph(R.navigation.nav_graph)

        NavigationUI.setupWithNavController(navView, navController)

        // https://stackoverflow.com/questions/70703505/navigationui-not-working-correctly-with-bottom-navigation-view-implementation
        navView.setOnItemSelectedListener{
            NavigationUI.onNavDestinationSelected(it, navController)
            navController.popBackStack(it.itemId, inclusive = false)
            true
        }
    }


}


// stateless
@Composable
private fun HelloContent(name: String, onNameChange: (String) -> Unit) {

    // state
//    var innerName: String by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        if(name.isNotEmpty()){
            Text(
                text = "Hello!",
                modifier = Modifier.padding(bottom = 20.dp),
                style = MaterialTheme.typography.body2
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
    }
}

@Composable // component
private fun Greeting(name: String) { // name: state
    // JSX return
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Red)
            .padding(24.dp)
    ) {
        Text(text = "Hello,")
        Text(text = name)
    }
}


@Composable
fun ArtistCardBox() {
    Box {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Composable
fun ArtistCardColumn() {
    Column {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Composable
fun ArtistCardRow() {
    Row {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Composable
private fun AlbumCover() {
    Column {
        Box(modifier = Modifier.size(160.dp)) {
            AsyncImage(
                model ="https://upload.wikimedia.org/wikipedia/en/d/d1/Stillfantasy.jpg",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds)
            Text(
                text = "Still Fantasy",
                color = Color.White,
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 4.dp)
                    .align(Alignment.BottomStart)
            )
        }
        Text(
            text = "Jay Chou",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            color = Color.LightGray
        )
    }
}

@Preview
@Composable
fun PreviewArtistCard() {
    SpotifyTheme {
        Surface {
            ArtistCardBox()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpotifyTheme {
        Surface {
            Greeting("Android")
        }
    }
}