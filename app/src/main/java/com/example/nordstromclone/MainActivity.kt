package com.example.nordstromclone


import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nordstromclone.ui.theme.NordstromCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NordstromCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffolder()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffolder() {
    Scaffold(
        topBar = {
            Surface(
                color = Color.LightGray
            ) {
                Column() {
                    Row() {
                        UpperAppBar()
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Row() {
                        LowerAppBar()
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                }
            }

        },
        bottomBar = {
            BottomBar()
        }
    ) {
        Body()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpperAppBar() {
    TopAppBar(
        title = {
            Image(modifier = Modifier
                .size(100.dp),
                painter = painterResource(id = R.drawable.nordstrom_logo_1),
                contentDescription = "")

            },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "Notifications")
            }
        },
        modifier = Modifier
            .background(color = Color.Gray)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LowerAppBar() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TopAppBar(
        title = {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                        },
                placeholder = { Text("Search for products and brands") },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )

        },
        navigationIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
        }
    )
}

data class Icon(
    val image_id: Int,
    val label: String,
    val tint: Color = Color.Gray
)

@Composable
fun BottomBar() {
    val bottomIcons: List<Icon> = listOf(
        Icon(R.drawable.shop, "Shop"),
        Icon(R.drawable.bag, "Bag"),
        Icon(R.drawable.wish, "Wish List"),
        Icon(R.drawable.account, "Account")
    )
    BottomAppBar() {
        bottomIcons.forEach {icon ->
        NavigationBarItem(selected = false, onClick = { /*TODO*/ },
            icon =  {Icon(painter = painterResource(id = icon.image_id),
                contentDescription = icon.label, tint = icon.tint)},
            label = {Text(text = icon.label, color = icon.tint)})
        }
    }
}

data class CarouselCard(
    val image_id: Int,
    val label: String,
    val size: Dp = 150.dp
)

val CarouselSlides: List<CarouselCard> = listOf(
    CarouselCard(R.drawable.woman, "Women"),
    CarouselCard(R.drawable.man, "Men"),
    CarouselCard(R.drawable.kids, "Kids"),
    CarouselCard(R.drawable.ya, "Young Adult"),
    CarouselCard(R.drawable.shoe, "Designer"),
    CarouselCard(R.drawable.pexels_photo_1303082, "Gifts")
)

@Composable
fun Body() {
    Column() {
        Spacer(modifier = Modifier.heightIn(150.dp))

        Row() {
            LazyRow(


            ) {
                CarouselSlides.forEach { card ->
                    item { 
                        Column(modifier = Modifier
                            .padding(10.dp),
                            horizontalAlignment = CenterHorizontally
                        ) {
                            Row() {
                                Image(painter = painterResource(id = card.image_id),
                                    contentDescription = card.label, modifier = Modifier
                                        .height(card.size))
                            }
                            Row(horizontalArrangement = Arrangement.Center) {
                                Text(text = card.label, textAlign = TextAlign.Center)
                            }
                        }
                    }
                    
                }

            }
        }
    }
}