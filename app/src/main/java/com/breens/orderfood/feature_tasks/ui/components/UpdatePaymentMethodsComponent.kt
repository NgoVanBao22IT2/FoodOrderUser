package com.breens.orderfood.feature_tasks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.breens.orderfood.R
import com.breens.orderfood.feature_tasks.state.OrderScreenUiState
import com.breens.orderfood.feature_tasks.state.TasksScreenUiState
import com.breens.orderfood.feature_tasks.state.optionsPayment
import com.breens.orderfood.theme.colorGreenDark
import com.example.movieui.core.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePaymentMethodsComponent(navController: NavHostController, uiStateOrder: OrderScreenUiState){
    val uiStateValue = remember { mutableStateOf(OrderScreenUiState()) }
    uiStateOrder.selectedOptionPayment = uiStateValue.value.selectedOptionPayment
    val scrollState = rememberScrollState()
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

                    navController.popBackStack()
                },
            ) {
                Text(text = "Chọn")
            }
        }

    ) { padding ->
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
                Text(text = "Chọn địa chỉ", style = MaterialTheme.typography.h6)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                optionsPayment.forEach { optionsPayment ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .selectable(
                                selected = uiStateValue.value.selectedOptionPayment == optionsPayment,
                                onClick = {
                                    uiStateValue.value =
                                        uiStateValue.value.copy(selectedOptionPayment = optionsPayment)
                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()



                        ) {
                            Text(text = "", modifier = Modifier
                                .background(color = Color.LightGray)
                                .fillMaxWidth()
                                .height(1.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(top=15.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .border(
                                            width = 1.dp,
                                            color = Color.White,
                                            shape = RoundedCornerShape(50.dp)
                                        )
                                        .clip(RoundedCornerShape(50.dp))
                                        .background(colorGreenDark),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_featured_play_list_24),
                                        contentDescription = "Play Icon",
                                        modifier = Modifier.size(30.dp), // Kích thước của Icon
                                        tint = Color.White // Màu của Icon
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(

                                    modifier = Modifier.weight(0.7f)
                                ) {
                                    Text(
                                        text = "${optionsPayment} ",
                                        color = Color.Black,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }



                            }

                        }
                        RadioButton(
                            selected =  uiStateValue.value.selectedOptionPayment == optionsPayment,
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = colorGreenDark,
                                unselectedColor = Color.Gray
                            )
                        )
                    }
                }
            }






        }
    }
}

@Composable
@Preview(showBackground = true)
fun DemoPayment() {
    Surface {
        UpdatePaymentMethodsComponent(
            navController = NavHostController(LocalContext.current),
            uiStateOrder = OrderScreenUiState()
        )
    }
}