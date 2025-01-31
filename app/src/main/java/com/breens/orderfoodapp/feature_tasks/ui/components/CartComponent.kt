package com.breens.orderfoodapp.feature_tasks.ui.components

import android.text.Html
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.breens.orderfoodapp.R
import com.breens.orderfoodapp.data.model.Task
import com.breens.orderfoodapp.feature_tasks.state.OrderScreenUiState
import com.breens.orderfoodapp.feature_tasks.state.SignInScreenUiState
import com.breens.orderfoodapp.feature_tasks.state.TasksScreenUiState
import com.breens.orderfoodapp.theme.colorGreenDark
import com.example.movieui.core.theme.FoodColor
import com.example.movieui.core.theme.Yellow
import com.google.accompanist.pager.ExperimentalPagerApi
import java.text.DecimalFormat

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CartComponent(navController: NavHostController,
                  uiStateAccount: SignInScreenUiState,
                  uiState: TasksScreenUiState,
                  foodCart : Task,
                  setUserID: (String) -> Unit,
                  setFoodCode: (String) -> Unit,
                  setOrderAddress:(String)->Unit,
                  setOrderPayment: (String) -> Unit,
                  setOrderQuantity: (Int) -> Unit,
                  setOrderTitle: (String) -> Unit,
                  setOrderImage: (String) -> Unit,
                  setOrderPrice: (Int) -> Unit,
                  setOrderTotal: (Int) -> Unit,
                  saveOrder: () -> Unit,
                  uiStateOrder: OrderScreenUiState,
                  ){
    val scrollState = rememberScrollState()
    val uiStateValue = remember { mutableStateOf(OrderScreenUiState()) }
    val quantityTotal = uiStateOrder.value + uiStateValue.value.valueCart
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorGreenDark
                ),
                shape = RoundedCornerShape(32.dp),
                onClick = {
                    saveOrder()
                    navController.popBackStack()
                    navController.popBackStack()
                },
            ) {

                Text(text = "Đặt ngay - ${DecimalFormat("#,###").format(quantityTotal*(foodCart.price)-(uiState.selectedOptionSale))}đ")
            }
        }

    ) { padding ->
        LazyColumn{
            items(uiStateAccount.accounts.size) { index ->
                setUserID(uiStateAccount.accounts[index].userID)
            }
        }
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 16.dp, vertical = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back Button")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Thanh toán", style = MaterialTheme.typography.h6)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)


            ) {
                Text(text = "", modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(1.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top=15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.address),
                        contentDescription = "Food Image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(width = 60.dp, height = 50.dp)
                            .padding(end = 10.dp)
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .clip(RoundedCornerShape(15.dp))
                    )
                    Column(

                        modifier = Modifier.weight(0.7f)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Nhà ở",
                                color = Color.Black,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(
                                        width = 1.dp,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            ) {


                                Text(
                                    text = "Mặc định",
                                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp),
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )

                            }
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(

                            text = uiStateOrder.currentAddressOrder,
                            color = Color.Black,
                            fontSize = 14.sp,

                            )
                        setOrderAddress(uiStateOrder.selectedOptionAddress)
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ){

                        Icon(
                            painter = painterResource(id = R.drawable.baseline_navigate_next_24),
                            contentDescription = "Add",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("UpdateAddressComponent")
                                }
                                .size(25.dp),
                            tint = Color.Gray
                        )


                    }

                }


            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)


            ) {
                Text(text = "", modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(1.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {

                    Text(
                        text = "Tổng số món",
                        style = MaterialTheme.typography.h6,
                    )
                    TextButton(onClick = { }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {


                            Text(
                                text = "Thêm món",
                                modifier = Modifier
                                    .padding(vertical = 2.dp, horizontal = 5.dp)
                                    .clickable {
                                        navController.popBackStack()
                                        navController.popBackStack()
                                    },
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )

                        }
                    }
                }



            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)


            ) {
                Text(text = "", modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(1.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top=15.dp)
                ) {
                    setFoodCode(foodCart.taskId)

                    setOrderImage(foodCart.image)
                    Image(
                        painter = rememberImagePainter(foodCart.image),
                        contentDescription = "Movie Image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(width = 100.dp, height = 90.dp)
                            .padding(end = 10.dp)
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .clip(RoundedCornerShape(15.dp))
                    )
                    Column(

                        modifier = Modifier.weight(0.7f)
                    ) {
                        Text(
                            text = foodCart.title,
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        setOrderTitle(foodCart.title)
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AndroidView(
                                factory = { context ->
                                    TextView(context).apply {
                                        text = Html.fromHtml("<string><b><span style = \"color:#F54748\"><big>${DecimalFormat("#,###").format(foodCart.price)}đ</big></span><span style = \"color:#000000\"><big><big></big></big></span></b></string>")
                                    }
                                }
                            )
                            setOrderPrice(foodCart.price)

                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(colorGreenDark)
                        ) {

                            Text(
                                text = "${uiStateOrder.value + uiStateValue.value.valueCart}x",
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp),

                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )


                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "Add",
                            modifier = Modifier
                                .clickable {
                                    uiStateValue.value =
                                        uiStateValue.value.copy(valueCart = uiStateValue.value.valueCart + 1)
                                }
                                .size(25.dp),
                            tint = colorGreenDark
                        )

                    }

                }
                Spacer(modifier = Modifier.height(25.dp))

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)


            ) {
                Text(text = "", modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(1.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top=15.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_featured_play_list_24),
                        contentDescription ="icon_1",
                        modifier = Modifier
                        .size(35.dp),
                        tint = colorGreenDark



                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.weight(0.7f)
                    ) {

                        Text(
                            text = uiStateOrder.currentPaymentOrder,
                            color = Color.Black,
                            fontSize = 15.sp,
                        )

                    }
                    setOrderPayment(uiStateOrder.selectedOptionPayment)
                    /*OutlinedTextField(
                        value = uiStateOrder.currentPaymentOrder,
                        onValueChange = { payment ->
                            setOrderPayment(payment)
                        },
                        label = { androidx.compose.material3.Text("Payment") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            cursorColor = Color.Black,
                        ),
                    )*/
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_navigate_next_24),
                            contentDescription = "Add",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("UpdatePaymentMethodsComponent")
                                }
                                .size(25.dp),
                            tint = Color.LightGray
                        )

                    }

                }


            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)


            ) {
                Text(text = "", modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(1.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top=15.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_price_change_24),
                        contentDescription ="icon_1",
                        modifier = Modifier
                        .size(35.dp),
                        tint = colorGreenDark

                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.weight(0.7f)
                    ) {

                        Text(
                            text = "Mã giảm giá",
                            color = Color.Black,
                            fontSize = 15.sp,
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_navigate_next_24),
                            contentDescription = "Add",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("UpdateDiscountComponent")
                                }
                                .size(25.dp),
                            tint = Color.LightGray
                        )

                    }

                }


            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)


            ) {
                Text(text = "", modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(1.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top=15.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(0.7f)
                    ) {

                        Text(
                            text = "Số tiền",
                            color = Color.Black,
                            fontSize = 15.sp,
                        )
                    }
                    Text(text = "${DecimalFormat("#,###").format(quantityTotal*(foodCart.price))}đ", fontSize = 18.sp, color = Color.Red)



                }


            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)


            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Column(
                        modifier = Modifier.weight(0.7f)
                    ) {

                        Text(
                            text = "Số tiền giảm",
                            color = Color.Black,
                            fontSize = 15.sp,
                        )
                    }
                    Text(text = "-${DecimalFormat("#,###").format(uiState.selectedOptionSale)}đ", fontSize = 18.sp, color = Color.Red)
                    setOrderQuantity(uiStateOrder.value)

                }


            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)


            ) {
                Text(text = "", modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(1.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top=15.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(0.7f)
                    ) {

                        Text(
                            text = "Tổng",
                            color = Color.Black,
                            fontSize = 15.sp,
                        )
                    }
                    Text(text = "${DecimalFormat("#,###").format(quantityTotal*(foodCart.price)-(uiState.selectedOptionSale))}đ", fontSize = 18.sp, color = Color.Red)
                    setOrderTotal(quantityTotal*(foodCart.price)-(uiState.selectedOptionSale))


                }


            }
            Spacer(modifier = Modifier.height(80.dp))
        }

    }
}

