package com.breens.orderfoodapp.feature_tasks.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.breens.orderfoodapp.R
import com.breens.orderfoodapp.theme.colorGreenDark
import com.breens.orderfoodapp.theme.colorWhite


@Composable
fun WelcomeAppFood(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else colorGreenDark)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.welcome),
                    contentDescription = "Welcome Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fast_food),
                        contentDescription = "logo",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(top = 20.dp, bottom = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Dịch vụ giao hàng nhanh",


                        color = Color.Gray,
                        style = MaterialTheme.typography.button,
                        modifier = Modifier
                    )
                    Text(
                        text = "Chào mừng khách hàng thân mến",                        color = Color.Gray,
                        style = MaterialTheme.typography.button,
                        modifier = Modifier.padding(bottom = 30.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            navController.navigate("Home")
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorGreenDark),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(start = 40.dp, end = 40.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text(
                            text = "Welcome",
                            color = colorWhite,
                            style = MaterialTheme.typography.button,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                        )
                    }

                }


            }

        }

    }

}

@Composable
@Preview
fun WelcomeScreenPreview() {

    WelcomeAppFood(navController = NavHostController(LocalContext.current))
}


