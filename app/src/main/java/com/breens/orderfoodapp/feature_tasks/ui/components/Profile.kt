package com.breens.orderfoodapp.feature_tasks.ui.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color.rgb
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.breens.orderfoodapp.R
import com.breens.orderfoodapp.data.model.UserProfile
import com.breens.orderfoodapp.feature_tasks.state.SignInScreenUiState
import com.breens.orderfoodapp.theme.Mediumseagreen
import com.breens.orderfoodapp.theme.colorGreenDark
import com.breens.orderfoodapp.theme.colorWhite
import kotlinx.coroutines.launch

@Composable
fun Profile(navController : NavController, uiStateAccount: SignInScreenUiState,) {
    val context = LocalContext.current
    val img: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.avatar)
    val bitmap = remember { mutableStateOf(img) }
    val scaledBitmap = bitmap.value.scaleDown(1024, true)

    val laucher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
    ) {
        if (it != null) {
            bitmap.value = it
        }
    }
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else colorWhite)
    )

    {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start

            ) {
                IconButton(onClick = {

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

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    bitmap = scaledBitmap.asImageBitmap(),
                    contentDescription = "Contact profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .clip(CircleShape)
                        .background(Color.White)

                )
                      /*  AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(UserProfile.avatar)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .width(120.dp)
                                .height(120.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                            .padding(top = 5.dp)
                        ) */
                Spacer(modifier = Modifier.height(0.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(uiStateAccount.accounts.isEmpty()) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),

                            onClick = { navController.navigate("SignInComponent") }) {
                            Text(
                                text = "Đăng nhập/Đăng ký",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 7.dp),

                            )
                        }
                    } else if(uiStateAccount.accounts.isNotEmpty()){
                        LazyColumn(
                            contentPadding = PaddingValues(12.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            items(uiStateAccount.accounts.size) { index ->
                                Text(
                                    text = "${uiStateAccount.accounts[index].firstName} ${uiStateAccount.accounts[index].lastName}",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = uiStateAccount.accounts[index].email,
                                    fontSize = 18.sp,

                                    )

                            }
                        }
                    }


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
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("CartComponent/{foodId}")

                    }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "",
                            tint = colorGreenDark
                        )
                    }
                    Text(
                        text = "Giỏ hàng",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 155.dp)
                    )
                    IconButton(onClick = {
                        navController.navigate("CartComponent")

                    }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_navigate_next_24), contentDescription = "",
                            tint = colorGreenDark)

                    }


                }


                Spacer(modifier = Modifier.height(0.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("UpdateAddressComponent")

                    }) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "",
                            tint = colorGreenDark



                        )
                    }
                    Text(
                        text = "Địa chỉ",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 176.dp)
                    )
                    IconButton(onClick = {
                        navController.navigate("UpdateAddressComponent")

                    }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_navigate_next_24), contentDescription = "",
                            tint = colorGreenDark)

                    }


                }

                Spacer(modifier = Modifier.height(0.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
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
                        text = "Sửa Profile",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 130.dp)
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
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("UpdatePaymentMethodComponent")

                    }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_payment_24),
                            contentDescription = "",
                            tint = colorGreenDark
                        )
                    }
                    Text(
                        text = "Thanh toán",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 130.dp)
                    )
                    IconButton(onClick = {

                        navController.navigate("UpdatePaymentMethodComponent")

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


//@Composable
//@Preview(showBackground = true)
//fun DemoProfile(){
//    Surface {
//
//
//    }
//}
