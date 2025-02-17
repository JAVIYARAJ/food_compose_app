package com.example.sampleapp.feature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavController
import com.example.sampleapp.R
import com.example.sampleapp.feature.components.common.CustomAppBar
import com.example.sampleapp.feature.components.common.CustomizedTextField
import com.example.sampleapp.feature.components.common.DishRatingWidget
import com.example.sampleapp.feature.components.common.LoadingWidget
import com.example.sampleapp.feature.components.common.MenuIcon
import com.example.sampleapp.feature.components.common.RoundedButton
import com.example.sampleapp.navigation.AppRoute
import com.example.sampleapp.ui.theme.alertConfirmColor
import com.example.sampleapp.ui.theme.backgroundColor
import com.example.sampleapp.ui.theme.cardColor
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavController) {

    val isLoading = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = Unit) {
        delay(1000)
        isLoading.value = false
    }

    val searchQuery= listOf("Pizza","Burger","Sushi","Ramen","Cake","Sweet","Biryani","Pasta")

    val foodCategoryList = listOf(
        FoodCategory(name = "Pizza", icon = R.drawable.ic_pizza_icon),
        FoodCategory(name = "Burger", icon = R.drawable.burger),
        FoodCategory(name = "Sushi", icon = R.drawable.sushi),
        FoodCategory(name = "Ramen", icon = R.drawable.ramen),
        FoodCategory(name = "Pizza", icon = R.drawable.ic_pizza_icon),
        FoodCategory(name = "Burger", icon = R.drawable.burger),
        FoodCategory(name = "Sushi", icon = R.drawable.sushi),
        FoodCategory(name = "Ramen", icon = R.drawable.ramen)
    )

    if (isLoading.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LoadingWidget()
        }
    } else {
        val focusManager= LocalFocusManager.current

        val query = remember {
            mutableIntStateOf(0)
        }

        LaunchedEffect(key1 = Unit) {
            while (true){
                delay(1500)
                if(query.intValue==searchQuery.size-1){
                    query.intValue=0
                }else{
                    query.intValue++
                }
            }
        }

        Scaffold(
            topBar = {
                CustomAppBar()
            },
            containerColor = backgroundColor
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .then(Modifier.padding(padding))
            ) {
                CustomizedTextField(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    value = "",
                    onValueChange = {

                    },
                    hint = "Search for '${searchQuery[query.intValue]}'",
                    radius = 8.dp,
                    imeCallBack = {
                        focusManager.clearFocus()
                    },
                    trailingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Clear text",
                                modifier = Modifier
                                    .size(25.dp)
                                    .clip(RoundedCornerShape(40.dp))
                                    .clickable { }
                            )
                            Box(modifier = Modifier.height(35.dp), contentAlignment = Alignment.Center) {
                                VerticalDivider(thickness = 1.dp)
                            }
                            Icon(
                                imageVector = Icons.Filled.Mic,
                                contentDescription = "Clear text",
                                modifier = Modifier.padding(end = 5.dp)
                                    .size(25.dp)
                                    .clip(RoundedCornerShape(40.dp))
                                    .clickable { },
                                tint = alertConfirmColor
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier
                        .heightIn(max = 200.dp, min = 150.dp)
                        .padding(top = 10.dp)
                        .fillMaxWidth(), colors = CardDefaults.cardColors().copy(containerColor = cardColor)
                ) {

                    val constraint = ConstraintSet {
                        val title = createRefFor("title")
                        val discount = createRefFor("discount")
                        val claimButton = createRefFor("claimButton")
                        val dishIcon = createRefFor("dishIcon")

                        constrain(title) {
                            start.linkTo(parent.start, margin = 10.dp)
                            top.linkTo(parent.top, margin = 10.dp)
                            bottom.linkTo(discount.top)
                        }

                        constrain(discount) {
                            start.linkTo(parent.start, margin = 10.dp)
                            top.linkTo(title.bottom, margin = 20.dp)
                            bottom.linkTo(claimButton.bottom)
                        }

                        constrain(claimButton) {
                            start.linkTo(parent.start, margin = 5.dp)
                            top.linkTo(discount.bottom, margin = 20.dp)
                            bottom.linkTo(parent.bottom, margin = 10.dp)
                        }

                        constrain(dishIcon) {
                            end.linkTo(parent.end, margin = 5.dp)
                            top.linkTo(parent.top, margin = 20.dp)
                            bottom.linkTo(parent.bottom)
                        }

                    }

                    val text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)) {
                            append("up to 85")
                        }
                        withStyle(style = SpanStyle(color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Normal)) {
                            append("%")
                        }

                    }

                    ConstraintLayout(constraintSet = constraint, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Get special discount",
                            style = TextStyle(color = Color.White, fontSize = 18.sp),
                            modifier = Modifier.layoutId("title"),
                            fontFamily = FontFamily.Serif
                        )
                        Text(
                            text = text,
                            style = TextStyle(color = Color.White, fontSize = 25.sp),
                            modifier = Modifier.layoutId("discount"),
                            fontFamily = FontFamily.Serif
                        )
                        RoundedButton(
                            onClick = { },
                            text = "Claim voucher",
                            modifier = Modifier.layoutId("claimButton"),
                            backgroundColor = Color.White,
                            contentColor = Color.Black
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_diet_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .layoutId("dishIcon")
                                .height(150.dp)
                                .width(150.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(modifier = Modifier.fillMaxWidth()) {

                    items(foodCategoryList.size) { index ->
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 8.dp)) {
                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = painterResource(id = foodCategoryList[index].icon),
                                    contentDescription = "",
                                    modifier = Modifier.size(45.dp)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = foodCategoryList[index].name,
                                    style = TextStyle(fontSize = 16.sp, color = Color.Black, fontFamily = FontFamily.SansSerif)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Popular food",
                        style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    )
                    Text(
                        text = "View all",
                        style = TextStyle(color = Color.Gray, fontSize = 18.sp),
                    )
                }

                LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                    items(10) {
                        FoodDishCard(onClick = {
                            navController.navigate(AppRoute.FoodDetailScreenRoute)
                        }, onCartClick = {
                            navController.navigate(AppRoute.CartScreenRoute)
                        })
                    }
                }
            }
        }
    }


}

@Preview
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            CustomAppBar()
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .then(Modifier.padding(padding))
        ) {
            Card(
                modifier = Modifier
                    .heightIn(max = 200.dp, min = 150.dp)
                    .padding(top = 10.dp)
                    .fillMaxWidth(), colors = CardDefaults.cardColors().copy(containerColor = cardColor),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp)
            ) {

                val constraint = ConstraintSet {
                    val title = createRefFor("title")
                    val discount = createRefFor("discount")
                    val claimButton = createRefFor("claimButton")
                    val dishIcon = createRefFor("dishIcon")

                    constrain(title) {
                        start.linkTo(parent.start, margin = 10.dp)
                        top.linkTo(parent.top, margin = 10.dp)
                        bottom.linkTo(discount.top)
                    }

                    constrain(discount) {
                        start.linkTo(parent.start, margin = 10.dp)
                        top.linkTo(title.bottom, margin = 20.dp)
                        bottom.linkTo(claimButton.bottom)
                    }

                    constrain(claimButton) {
                        start.linkTo(parent.start, margin = 5.dp)
                        top.linkTo(discount.bottom, margin = 20.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    }

                    constrain(dishIcon) {
                        end.linkTo(parent.end, margin = 5.dp)
                        top.linkTo(parent.top, margin = 20.dp)
                        bottom.linkTo(parent.bottom)
                    }

                }

                val text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)) {
                        append("up to 85")
                    }
                    withStyle(style = SpanStyle(color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Normal)) {
                        append("%")
                    }

                }

                ConstraintLayout(constraintSet = constraint, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Get special discount",
                        style = TextStyle(color = Color.White, fontSize = 18.sp),
                        modifier = Modifier.layoutId("title"),
                        fontFamily = FontFamily.Serif
                    )
                    Text(
                        text = text,
                        style = TextStyle(color = Color.White, fontSize = 25.sp),
                        modifier = Modifier.layoutId("discount"),
                        fontFamily = FontFamily.Serif
                    )
                    RoundedButton(
                        onClick = { },
                        text = "Claim voucher",
                        modifier = Modifier.layoutId("claimButton"),
                        backgroundColor = Color.White,
                        contentColor = Color.Black
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_diet_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .layoutId("dishIcon")
                            .height(150.dp)
                            .width(150.dp),
                        contentScale = ContentScale.Fit
                    )
                }

            }
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                CustomizedTextField(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .weight(1f),
                    value = "",
                    onValueChange = {

                    },
                    hint = "Search your food..",
                    leadingIcon = {
                        Image(painter = painterResource(id = R.drawable.ic_search_icon), contentDescription = "", modifier = Modifier.size(20.dp))
                    }
                )
                MenuIcon(menuIcon = R.drawable.ic_filter_icon, contentDescription = "filter", onClick = {

                })
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(3) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 5.dp)) {
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(painter = painterResource(id = R.drawable.ic_pizza_icon), contentDescription = "")
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Pizza", style = TextStyle(fontSize = 14.sp, color = Color.Black, fontFamily = FontFamily.SansSerif))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Popular food",
                    style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold),
                )
                Text(
                    text = "View all",
                    style = TextStyle(color = Color.Gray, fontSize = 18.sp),
                )
            }

            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                items(10) {
                    FoodDishCard(onClick = {

                    }, onCartClick = {

                    })
                }
            }
        }
    }
}

@Composable
fun FoodDishCard(modifier: Modifier = Modifier, onClick: () -> Unit, onCartClick: () -> Unit) {
    Surface(
        color = Color.White, shape = RoundedCornerShape(20.dp), modifier = Modifier
            .padding(0.dp)
            .then(
                Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        onClick.invoke()
                    })
    ) {

        val constraint = ConstraintSet {
            val starIcon = createRefFor("starIcon")
            val ratingText = createRefFor("ratingText")
            val dishIcon = createRefFor("dishIcon")
            val dishName = createRefFor("dishName")
            val dishPrice = createRefFor("dishPrice")
            val addCartIcon = createRefFor("addCartIcon")

            constrain(ratingText) {
                end.linkTo(parent.end, 5.dp)
                top.linkTo(starIcon.top)
                bottom.linkTo(starIcon.bottom)
            }

            constrain(starIcon) {
                end.linkTo(ratingText.start, margin = 5.dp)
            }

            constrain(dishIcon) {
                top.linkTo(starIcon.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(dishName) {
                top.linkTo(dishIcon.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(dishPrice) {
                start.linkTo(parent.start)
                top.linkTo(dishName.bottom, margin = 5.dp)
                bottom.linkTo(parent.bottom)
            }

            constrain(addCartIcon) {
                top.linkTo(dishName.bottom, margin = 5.dp)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }

        }

        ConstraintLayout(
            constraintSet = constraint, modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            val dishPrice = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 16.sp, fontFamily = FontFamily.Monospace,
                    )
                ) {
                    append("$")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                    )
                ) {
                    append(" 45.5")
                }

            }

            DishRatingWidget(modifier = Modifier.layoutId("starIcon"))

            Image(
                painter = painterResource(id = R.drawable.ic_diet_icon),
                contentDescription = "start_icon",
                contentScale = ContentScale.Inside,
                modifier = Modifier.layoutId("dishIcon")
            )

            Text(
                text = "Original Sushi",
                style = TextStyle(color = Color.Black),
                modifier = Modifier.layoutId("dishName"),
                fontSize = 18.sp,
                maxLines = 2,
            )

            Text(text = dishPrice, style = TextStyle(color = Color.Black), modifier = Modifier.layoutId("dishPrice"), fontSize = 18.sp)


            MenuIcon(
                menuIcon = R.drawable.ic_cart_icon,
                contentDescription = "add_to_cart",
                modifier = Modifier.layoutId("addCartIcon"),
                colorFilter = ColorFilter.tint(color = Color.White),
                menuColor = Color.Black,
                size = 40.dp, onClick = {
                    onCartClick.invoke()
                }
            )
        }

    }
}

data class FoodCategory(
    val name: String,
    val icon: Int
)