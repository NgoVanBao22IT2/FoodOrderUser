package com.breens.orderfoodapp.feature_tasks.ui.components

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.breens.orderfoodapp.R
import com.breens.orderfoodapp.data.model.UserProfile
import com.breens.orderfoodapp.feature_tasks.viewmodel.EditProfileViewModel
import com.breens.orderfoodapp.theme.colorGreenDark
import com.breens.orderfoodapp.theme.colorWhite
import com.breens.orderfoodapp.theme.colorWhiteGray

import java.text.SimpleDateFormat
import java.util.Date


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(
    navController : NavHostController,

    editProfileViewModel: EditProfileViewModel = viewModel(),

    ) {
    val user by editProfileViewModel.uiState.collectAsState()
    val format = SimpleDateFormat("dd MMM yyyy")
    var isDateOfBirthPicker by remember { mutableStateOf(false) }
    var isChooseImage by remember { mutableStateOf(false) }
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

    val launchImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        } else {
            val source = it?.let { it1 ->
                ImageDecoder.createSource(context.contentResolver, it1)
            }
            bitmap.value = source?.let { it1 -> ImageDecoder.decodeBitmap(it1) }!!
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorWhite)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        tint = Color.Black
                    )

                }

                Text(
                    text = "Profile",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(end = 170.dp)
                )
//                BadgedBox(
//                    badge = {
//                        Badge { Text("4") }
//                    },
//                    modifier = Modifier
//                        .padding(end = 20.dp)
//                ) {
//
//
//                }

                


            }




            Row {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorGreenDark)
                            .padding(top = 20.dp, bottom = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = scaledBitmap.asImageBitmap(),
                            contentDescription = "avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(120.dp)
                                .height(120.dp)
                                .clip(CircleShape)
                                .background(Color.White)

                        )
//                        AsyncImage(
//                            model = ImageRequest.Builder(LocalContext.current)
//                                .data(user.avatar)
//                                .crossfade(true)
//                                .build(),
//                            contentDescription = "Avatar",
//                            modifier = Modifier
//                                .width(120.dp)
//                                .height(120.dp)
//                                .clip(CircleShape)
//                                .background(Color.White)
//                            .padding(top = 5.dp)
//                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = { isChooseImage = true },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorWhiteGray.copy(alpha = 0.2f),
                                contentColor = Color.White
                            ),
                        ) {
                            Text(
                                text = "Change image",
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp
                            )
                        }

                    }
                    //
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = user.fullName,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = colorWhiteGray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        label = { Text(text = "Full name") },
                        shape = RoundedCornerShape(14.dp),
                        onValueChange = {
                            editProfileViewModel.updateFullName(it)
                        })

                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = user.phoneNumber,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = colorWhiteGray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        label = { Text(text = "Phone") },
                        shape = RoundedCornerShape(14.dp),
                        onValueChange = { editProfileViewModel.updatePhoneNumber(it) },
                    )


                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = user.email,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = colorWhiteGray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        label = { Text(text = "Email") },
                        shape = RoundedCornerShape(14.dp),
                        onValueChange = { editProfileViewModel.updateEmail(it) },
                    )
//


                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = { isDateOfBirthPicker = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorWhiteGray,
                            contentColor = Color.Black
                        ),
                        border = BorderStroke(0.dp, Color.Transparent),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Date of Birth",

                                style = MaterialTheme.typography.labelLarge,
                                color = Color.Black,
                            )
                            Spacer(modifier = Modifier.width(140.dp))
                            Text(
                                text = format.format(Date(user.dateOfBirth)),
                                textAlign = TextAlign.Left,
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                    if (isDateOfBirthPicker) {
                        val datePickerState = rememberDatePickerState()
                        val confirmEnable =
                            derivedStateOf { datePickerState.selectedDateMillis != null }

                        DatePickerDialog(
                            onDismissRequest = { isDateOfBirthPicker = false },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        isDateOfBirthPicker = false
                                        var date = datePickerState.selectedDateMillis
                                            ?: System.currentTimeMillis()
                                        editProfileViewModel.updateDateOfBirth(date)
                                    },
                                    enabled = confirmEnable.value
                                ) {
                                    Text("Ok")
                                }
                            }) {
                            DatePicker(state = datePickerState)
                        }
                    }

                    //
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Gender",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(start = 15.dp),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectableGroup(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.White),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = user.gender == 1,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = colorGreenDark, // Màu nền khi nút được chọn
                                    unselectedColor = Color.Gray // Màu nền khi nút không được chọn
                                ),
                                onClick = { editProfileViewModel.updateGender(1) })
                            Text(
                                text = "Male",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.White),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = user.gender == 0,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = colorGreenDark, // Màu nền khi nút được chọn
                                    unselectedColor = Color.Gray // Màu nền khi nút không được chọn
                                ),
                                onClick = { editProfileViewModel.updateGender(0) })
                            Text(
                                text = "Female",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Black
                            )
                        }
                    }
                    //
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(horizontal = 70.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorGreenDark,
                            ),
                            shape = RoundedCornerShape(5.dp),
                            onClick = {

                            }
                        ) {
                            Text(
                                text = "SAVE",
                                letterSpacing = 2.sp,
                            )
                        }
                    }
                }


            }
        }
        if (isChooseImage) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable {
                        isChooseImage = false
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .padding(vertical = 24.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(onClick = {
                                laucher.launch()
                                isChooseImage = false
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                                    contentDescription = "Camera",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            Text(
                                text = "Camera",
                                fontSize = 24.sp
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(onClick = {
                                launchImage.launch("image/*")
                                isChooseImage = false
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_image_24),
                                    contentDescription = "Gallery",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            Text(
                                text = "Gallery",
                                fontSize = 24.sp
                            )
                        }
                    }


                }
            }
        }
    }
}
@Composable
fun Bitmap.scaleDown(maxSize: Int, filter: Boolean): Bitmap {
    val ratio = maxSize.toFloat() / maxOf(this.width, this.height)
    val width = (this.width * ratio).toInt()
    val height = (this.height * ratio).toInt()
    return Bitmap.createScaledBitmap(this, width, height, filter)
}

@Composable
fun AsyncImage(model: Any, contentDescription: String, modifier: Modifier) {

}

@Composable
@Preview(showBackground = true)
fun DemoEditProfile() {
    Surface {
        EditProfile(navController = NavHostController(LocalContext.current))

    }
}