package com.example.artspace

import android.os.Bundle
import android.widget.ScrollView
import android.widget.Scroller
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var image by remember { mutableStateOf(1) }
    val img = Img(x = image)
    val src = Src(x = image)
    val artist = Artist(x = image)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(
                border = BorderStroke(32.dp, color = Color.White),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
            ) {
                Image(
                    painter = painterResource(img),
                    contentDescription = null
                )
            }
            Surface(color = Color(204, 203, 204, 255)) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Row {
                        Text(text = "By ", fontSize = 24.sp)
                        Text(
                            text = stringResource(id = artist),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                    Row {
                        Text(text = "Source- ")
                        Text(text = stringResource(id = src), fontWeight = FontWeight.Bold)
                    }
                }
            }

        }
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row() {
                Box() {
                    Button(onClick = {
                        when (image) {
                            1 -> {
                                image = 4
                            }

                            2 -> {
                                image = 1
                            }

                            3 -> {
                                image = 2
                            }

                            4 -> {
                                image = 3
                            }
                        }
                    }) {
                        Text(text = "Previous")
                    }
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Button(onClick = {
                        when (image) {
                            1 -> {
                                image = 2
                            }

                            2 -> {
                                image = 3
                            }

                            3 -> {
                                image = 4
                            }
                            4 -> {
                                image = 1
                            }
                        }
                    }) {
                        Text(text = "Next")
                    }
                }

            }
        }
    }

}

@Composable
fun Img(x: Int): Int {
    val result = when (x) {
        1 -> R.drawable.alexander_ant
        2 -> R.drawable.dids
        3 -> R.drawable.eberhard_grossgasteiger
        else -> R.drawable.europeana
    }
    return result
}

@Composable
fun Artist(x: Int): Int {
    val result = when (x) {
        1 -> R.string.artist1
        2 -> R.string.artist2
        3 -> R.string.artist3
        else -> R.string.artist4
    }
    return result
}

@Composable
fun Src(x: Int): Int {
    val result = when (x) {
        1, 2, 3 -> R.string.pexels
        else -> R.string.unsplash
    }
    return result
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}