package com.example.sampleapp.feature.components.common

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampleapp.R
import com.example.sampleapp.ui.theme.alertConfirmColor
import kotlinx.coroutines.delay

@Composable
fun MenuIcon(
    modifier: Modifier = Modifier,
    menuIcon: Int,
    contentDescription: String,
    scaleType: ContentScale = ContentScale.Inside,
    menuColor: Color = Color.White,
    colorFilter: ColorFilter? = null,
    size: Dp = 50.dp,
    onClick: () -> Unit
) {
    Surface(modifier = modifier
        .size(size)
        .clip(RoundedCornerShape(20.dp))
        .clickable {
            onClick.invoke()
        }, shape = RoundedCornerShape(50.dp), color = menuColor
    ) {
        Image(painter = painterResource(id = menuIcon), contentDescription = contentDescription, contentScale = scaleType, colorFilter = colorFilter)
    }
}

@Composable
fun CustomizedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    hintStyle: TextStyle = TextStyle(color = Color.Gray),
    leadingIcon: @Composable (() -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Go,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeCallBack: (() -> Unit)? = null,
    radius: Dp=40.dp,
    trailingIcon: @Composable (() -> Unit)? = null
) {

    val customShape: Shape = RoundedCornerShape(radius)

    OutlinedTextField(modifier = modifier.border(BorderStroke(0.8.dp, color = Color.Gray), shape = customShape),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        placeholder = {
            Text(text = hint, style = hintStyle)
        },
        shape = customShape,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedTrailingIconColor = Color.Black,
            unfocusedTrailingIconColor = Color.Black
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                imeCallBack?.invoke()
            },
            onDone = {
                imeCallBack?.invoke()
            },
            onSearch = {
                imeCallBack?.invoke()
            },
            onNext = {
                imeCallBack?.invoke()
            },
            onSend = {
                imeCallBack?.invoke()
            },
            onPrevious = {
                imeCallBack?.invoke()
            }
        ),
        trailingIcon = trailingIcon
    )
}

@Composable
fun RoundedButton(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color = Color.Blue,
    contentColor: Color = Color.White,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    roundedValue: Int = 20
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = ButtonDefaults.TextButtonContentPadding,
        shape = RoundedCornerShape(roundedValue),
        modifier = modifier
            .padding(5.dp)
            .height(45.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CustomAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(all = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_airplane_icon),
                    contentDescription = "menu_icon",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(35.dp)
                )
                Text(text = "Esparkbiz", style = TextStyle(color = Color.Gray, fontSize = 18.sp, fontWeight = FontWeight.Bold))
            }
            Text(text = "Science city,Near Krishan farm, Ahmedabad 362610", style = TextStyle(color = Color.Gray, fontSize = 16.sp, textAlign = TextAlign.Center), maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(5.dp))
        }
        MenuIcon(menuIcon = R.drawable.ic_profile_icon, contentDescription = "notification_icon", onClick = {

        })
    }
}


@Composable
fun DishRatingWidget(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_star_icon),
            contentDescription = "start_icon",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(25.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "4.5", style = TextStyle(color = Color.Gray), fontSize = 16.sp)
    }
}

@Composable
fun DishPriceIndicatorWidget(modifier: Modifier = Modifier, dishPrice: Double, fontSize: TextUnit = 20.sp) {

    val dishPriceString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 16.sp, fontFamily = FontFamily.Monospace
            )
        ) {
            append("$")
        }
        withStyle(
            style = SpanStyle(
                fontSize = fontSize, fontWeight = FontWeight.Bold
            )
        ) {
            append(" $dishPrice")
        }
    }
    Text(text = dishPriceString, style = TextStyle(color = Color.Black), modifier = modifier, fontSize = 18.sp)
}


@Composable
fun ElevatedButtonWidget(
    modifier: Modifier = Modifier,
    buttonColor: Color,
    radius: Dp = 20.dp,
    title: String,
    textStyle: TextStyle = TextStyle(color = Color.White),
    fontSize: TextUnit = 20.sp
) {
    ElevatedCard(
        shape = RoundedCornerShape(radius), modifier = modifier, colors = CardDefaults.cardColors(containerColor = buttonColor)
    ) {
        Text(
            text = title,
            style = textStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 15.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = fontSize
        )
    }
}

@Composable
fun AlertDialogWidget(
    modifier: Modifier = Modifier,
    title: String,
    confirmButtonTitle: String = "Yes",
    cancelButtonTitle: String = "No",
    description: String,
    onConfirmTap: () -> Unit,
    isShow: Boolean = false,
    onDismissTap: () -> Unit
) {
    if (isShow) {
        AlertDialog(
            onDismissRequest = {
                onDismissTap()
            }, shape = RoundedCornerShape(15.dp),
            icon = {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "")
            },
            title = {
                Text(text = title, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, fontFamily = FontFamily.SansSerif))
            },
            text = {
                Text(
                    text = description,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, fontFamily = FontFamily.SansSerif)
                )
            },
            tonalElevation = 5.dp,
            dismissButton = {
                TextButton(onClick = {
                    onDismissTap()
                }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), shape = RoundedCornerShape(10.dp)) {
                    Text(text = cancelButtonTitle, style = TextStyle(color = alertConfirmColor, fontWeight = FontWeight.Bold))
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onConfirmTap()
                }, colors = ButtonDefaults.buttonColors(containerColor = alertConfirmColor), shape = RoundedCornerShape(10.dp)) {
                    Text(text = confirmButtonTitle, style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold))
                }
            }
        )
    }

}

@Preview
@Composable
fun LoadingWidget(modifier: Modifier = Modifier) {

    val degree = produceState(initialValue = 0) {
        while (true) {
            delay(50)
            value = (value + 10) % 360
        }
    }

    Box(modifier = Modifier.size(70.dp), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.ic_loading_icon), contentDescription = "", modifier = Modifier
            .fillMaxSize()
            .rotate(degree.value.toFloat()))
    }
}


@Preview(showSystemUi = true)
@Composable
fun NavigationDrawerWidget(modifier: Modifier = Modifier) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    
    ModalNavigationDrawer(drawerContent = {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(horizontal = 50.dp)) {
                Image(painter = painterResource(id = R.drawable.ic_burger_dish_icon), contentDescription = "", contentScale = ContentScale.FillBounds, modifier = Modifier.fillMaxSize())
            }
        }
    }, drawerState = drawerState) {

    }
}