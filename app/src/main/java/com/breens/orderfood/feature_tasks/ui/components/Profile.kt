package com.breens.orderfood.feature_tasks.ui.components

import android.graphics.Color.rgb
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.breens.orderfood.R
import com.breens.orderfood.feature_tasks.state.SignInScreenUiState
import com.breens.orderfood.theme.Mediumseagreen
import com.breens.orderfood.theme.colorGreenDark
import com.breens.orderfood.theme.colorWhite

@Composable
fun Profile(navController: NavController, uiStateAccount: SignInScreenUiState,) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else colorWhite)
    )

    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start

            ) {
                IconButton(onClick = {
                    navController.navigate("Menu")

                }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_person_pin_24),
                        contentDescription = "",
                        tint = colorGreenDark,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 10.dp),)

                }
                Text(
                    text = "Profile",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 7.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.foodizone_logo),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .border(BorderStroke(1.dp, Color.Gray), shape = CircleShape)
                        .clip(CircleShape)
                        .size(110.dp)


                )
                Spacer(modifier = Modifier.height(0.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ngo Van Bao",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = "baonv.22it@vku.udn.vn",
                        fontSize = 18.sp,

                        )
                    Divider(
                        modifier = Modifier.padding(vertical = 15.dp),
                        color = Color(rgb(211, 211, 211)),
                        thickness = 1.dp
                    )
                }
                Spacer(modifier = Modifier.height(0.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("CardScreen")

                    }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "",
                            tint = colorGreenDark
                        )
                    }
                    Text(
                        text = "My Card",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 207.dp)
                    )
                    IconButton(onClick = {
                        navController.navigate("CardScreen")

                    }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "",
                            tint = colorGreenDark

                        )

                    }


                }

                Spacer(modifier = Modifier.height(0.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("")

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "",
                            tint = colorGreenDark
                        )
                    }
                    Text(
                        text = "My Notification",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 138.dp)
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "",
                            tint = colorGreenDark

                        )

                    }


                }

                Spacer(modifier = Modifier.height(0.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("DeliverTo")

                    }) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "",
                            tint = colorGreenDark



                        )
                    }
                    Text(
                        text = "Address",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 207.dp)
                    )
                    IconButton(onClick = {
                        navController.navigate("DeliverTo")

                    }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "",
                            tint = colorGreenDark


                        )

                    }


                }

                Spacer(modifier = Modifier.height(0.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("EditProfile")

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "",
                            tint = colorGreenDark

                        )
                    }
                    Text(
                        text = "Edit Profile",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 180.dp)
                    )
                    IconButton(onClick = {
                        navController.navigate("EditProfile")

                    }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "",
                            tint = colorGreenDark

                        )

                    }


                }

                Spacer(modifier = Modifier.height(0.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("Payment")

                    }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_payment_24),
                            contentDescription = "",
                            tint = colorGreenDark
                        )
                    }
                    Text(
                        text = "Payment",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 202.dp)
                    )
                    IconButton(onClick = {
                        navController.navigate("Payment")

                    }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "",
                            tint = colorGreenDark

                        )

                    }


                }

                Spacer(modifier = Modifier.height(0.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("Security")

                    }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_security_24),
                            contentDescription = "",
                            tint = colorGreenDark
                        )
                    }
                    Text(
                        text = "Security",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 208.dp)
                    )
                    IconButton(onClick = {
                        navController.navigate("Security")

                    }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "",
                            tint = colorGreenDark

                        )

                    }


                }

                Spacer(modifier = Modifier.height(0.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("Language")

                    }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_language_24),
                            contentDescription = "",
                            tint = colorGreenDark
                        )
                    }
                    Text(
                        text = "Language",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 193.dp)
                    )
                    IconButton(onClick = {
                        navController.navigate("Language")

                    }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "",
                            tint = colorGreenDark

                        )

                    }


                }



            }


        }
    }
}
