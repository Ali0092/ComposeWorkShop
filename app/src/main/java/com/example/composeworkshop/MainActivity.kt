package com.example.composeworkshop

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeworkshop.ui.theme.ComposeWorkShopTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWorkShopTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GenderSelectAnimation()
//                    ScaleAndColorAnimation()
//                    ColorAnimation()
                }
            }
        }
    }
}


@Composable
fun GenderSelectAnimation() {
    val female = remember { mutableStateOf(true) }
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.male),
            contentDescription = "Male Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(animateDpAsState(if (female.value) 100.dp else 250.dp).value)
                .border(width = animateDpAsState(if (female.value) 0.dp else 4.dp).value,
                    color = animateColorAsState(if (female.value) Color.Transparent else Color.Red).value
                )
                .padding(8.dp)
                .clickable { female.value = !female.value }
                .clip(RoundedCornerShape(animateDpAsState(if (female.value) 0.dp else 8.dp).value))
        )
        Image(
            painter = painterResource(R.drawable.femalwe),
            contentDescription = "Female Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(animateDpAsState(if (!female.value) 100.dp else 250.dp).value)
                .border(width = animateDpAsState(if (!female.value) 0.dp else 4.dp).value,
                    color = animateColorAsState(if (!female.value) Color.Transparent else Color.Red).value)
                .padding(8.dp)
                .clickable { female.value = !female.value }
                .clip(RoundedCornerShape(animateDpAsState(if (!female.value) 0.dp else 8.dp).value))
        )
    }

}


@Composable
fun ScaleAndColorAnimation() {
    val enabled = remember { mutableStateOf(true) }
    val color: Color by animateColorAsState(
        if (enabled.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
        label = "color_state"
    )
    val height: Dp by animateDpAsState(if (enabled.value) 40.dp else 60.dp, label = "height_state")
    val width: Dp by animateDpAsState(if (enabled.value) 150.dp else 300.dp, label = "width_state")
    Button(
        onClick = { enabled.value = !enabled.value },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier
            .padding(16.dp)
            .height(height)
            .width(width),
    ) {
        Text("Scale & Color")
    }
}











@Composable
fun ColorAnimation() {
    val enabled = remember { mutableStateOf(true) }
    val color  by animateColorAsState(
        if (enabled.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
        label = "color_state"
    )

    Button(
        onClick = { enabled.value = !enabled.value },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Text("Color Animation")
    }
}





@Composable
fun HeartBeatDemo() {
    val animScale = remember { Animatable(initialValue = 1f) }
    val animColor = remember { Animatable(initialValue = Color.Red) }


    LaunchedEffect(animScale) {
        animScale.animateTo(
            targetValue = 2.5f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = EaseInBounce, delayMillis = 1200),
                repeatMode = RepeatMode.Reverse
            )
        )
    }
    LaunchedEffect(animColor) {
        animColor.animateTo(
            targetValue = Color.Black,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = EaseInOutElastic, delayMillis = 1200),
                repeatMode = RepeatMode.Reverse
            )
        )
    }


    Image(imageVector = Icons.Default.Favorite,
        contentDescription = "Favourite Icon",
        modifier = Modifier
            .size((60 * animScale.value).dp)
            .padding(10.dp),
        colorFilter = ColorFilter.tint(animColor.value)
    )

}

@Composable
fun MovingSquare() {
    val animPosX = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(animPosX) {
        animPosX.animateTo(
            targetValue = 1500f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, delayMillis = 1000, easing = EaseInOutElastic),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        withTransform({
            translate(left = animPosX.value)
        }) {
            drawRect(color = Color.Red)
        }
    })
}

























@Composable
fun OutLineTextField() {
    var text  =  remember { mutableStateOf("") }
    OutlinedTextField(
        value = text.value,
        trailingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = null) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        label = { Text(text = "Enter Email") },
        placeholder = { Text(text = "Enter your Phone") },
        onValueChange = {
            text.value = it
        }
    )
}

@Composable
fun SimpleTextField() {
    var text =  remember { mutableStateOf("") }
    TextField(
        value = text.value,
        onValueChange = { newText ->
            text.value = newText.toString()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        label = { Text(text = "Your Label") },
        placeholder = { Text(text = "Email") }
    )
}

/*
*  val items = listOf(
                "item 1",
                "item 2",
                "item 3",
                "item 4",
            )

            ComposeWorkShopTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet {
                            items.forEachIndexed { index, s ->
                                NavigationDrawerItem(
                                    label = { Text(
                                        text = s.toString(),
                                        modifier = Modifier.padding(8.dp),
                                        color = Color.Gray
                                    ) },
                                    selected = false,
                                    onClick = {  })
                            }

                        }
//                        NavDrawer()
                    },
                    drawerState = drawerState,
                    gesturesEnabled = true
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Top App Bar ")
                                },
                                navigationIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "menu_icon",
                                        modifier = Modifier
                                            .padding(start = 15.dp, end = 10.dp)
                                            .clickable {
                                                scope.launch {
                                                    drawerState.apply {
                                                        if (drawerState.isClosed) open() else close()
                                                    }
                                                }
                                            }
                                    )
                                },
                                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Gray)
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = {

                            }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "FAB ICON",
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                        },
                        bottomBar = { BottomBar() }
                    ) {
                        //...
                    }

                }


            }
        }
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {

}

@Composable
fun BottomBar() {
    BottomAppBar {
        Text(text = "Bottom App Bar")
    }
}

@Composable
fun NavDrawer() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
            .width(200.dp)
            .background(color = Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        repeat(5) { item ->

        }
    }
}

@Composable
fun FAB(drawerState: DrawerState, onMenuButtonClicked: () -> Unit) {
    FloatingActionButton(onClick = {
        onMenuButtonClicked()
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "FAB ICON",
            modifier = Modifier.size(20.dp),
        )
    }
}


@Composable
fun SimpleButton() {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.White
        ),
        onClick = {

        },
        shape = RoundedCornerShape(
            topStart = 25.dp,
            topEnd = 25.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        elevation = ButtonDefaults.buttonElevation(
            15.dp,
            10.dp,
            0.dp,
            0.dp,
            0.dp
        )

    ) {
        Icon(imageVector = Icons.Default.Favorite, contentDescription = "favourite_icon")

    }
}

@Composable
fun animatedFab() {
    val expanded = remember { mutableStateOf(false) }

    FloatingActionButton(onClick = {
        expanded.value = !expanded.value
    }, modifier = Modifier.padding(10.dp)) {
        Row(Modifier.padding(start = 16.dp, end = 16.dp)) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Icon",
                Modifier.align(Alignment.CenterVertically)
            )
            AnimatedVisibility(
                visible = expanded.value,
                modifier = Modifier.align(Alignment.CenterVertically),
                enter = slideInVertically(
                    initialOffsetY = { 300 },
                    animationSpec = tween(500)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { -300 },
                    animationSpec = tween(700)
                )
            ) {
                Text(modifier = Modifier.padding(start = 8.dp), text = "Like")
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeWorkShopTheme {
        Greeting("Android")
    }
}


@Composable
fun ExpandableText() {
    val shortText = "Click me"
    val longText = "Very long text passage that spans\nacross multiple lines, paragraphs\nand pages"
    var short = remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                Color.Blue,
                RoundedCornerShape(15.dp)
            )
            .clickable { short.value = !short.value }
            .padding(20.dp)
            .animateContentSize(
                animationSpec = spring(
                    stiffness = Spring.StiffnessHigh,
                    dampingRatio = Spring.DampingRatioMediumBouncy
                )

            )
        //, dampingRatio = Spring.DampingRatioMediumBouncy

        //tween( durationMillis = 300, easing = Ease)
    ) {
        Text(
            if (short.value) {
                shortText
            } else {
                longText
            },
            style = TextStyle(color = Color.White)
        )
    }
}


@Composable
fun PortraitModeImage() {
    var portraitMode = remember { mutableStateOf(true) }
    Box(
        Modifier
            .clickable { portraitMode.value = !portraitMode.value }
            .sizeIn(maxWidth = 300.dp, maxHeight = 300.dp)
            .background(if (portraitMode.value) Color(0xFFfffbd0) else Color(0xFFe3ffd9))
            .animateContentSize(
                animationSpec = tween(500, easing = LinearEasing),
                finishedListener = { startSize, endSize ->
                    Log.d("droidcon", "$startSize -> $endSize")
                }
            )
            .aspectRatio(if (portraitMode.value) 3 / 4f else 16 / 9f)
    ) {
        Text(
            if (portraitMode.value) {
                "3 : 4"
            } else {
                "16 : 9"
            },
            style = TextStyle(color = Color.Black)
        )
    }
}
