package com.breens.orderfood.feature_tasks.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.breens.orderfood.R
import com.breens.orderfood.feature_tasks.state.SignInScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInComponent(
    uiStateAccount: SignInScreenUiState,
    navController: NavController,
    setEmail: (String)-> Unit,
    setPassword: (String)-> Unit,
    saveAccount: () -> Unit,
){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.fast_food), contentDescription = "",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Login to your account", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiStateAccount.currentEmail ,
             onValueChange = {email->
                setEmail(email)
            },
            label = { Text(text = "Email") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                cursorColor = Color.Black,
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiStateAccount.currentPassword,
            onValueChange = {pass->
                setPassword(pass)
            } ,
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                cursorColor = Color.Black,
            ),

        )

        Spacer(modifier = Modifier.height(20.dp))


        Button(onClick = {
            saveAccount()
        }, colors = ButtonDefaults.buttonColors(Color(0xFFFF9F00))) {
            Text(
                text = "Sign In",
                modifier = Modifier.width(250.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Forgot Password?", modifier = Modifier.clickable { })

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Or sign up with")

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(painter = painterResource(id = R.drawable.google), contentDescription = "",
                modifier = Modifier.clickable { })

            Image(painter = painterResource(id = R.drawable.facebook), contentDescription = "",
                modifier = Modifier.clickable { })

            Image(painter = painterResource(id = R.drawable.twitter), contentDescription = "",
                modifier = Modifier.clickable { })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(text = "Already have an account ?")

            Text(text = " Sign Up",
                color = Color(0xFFFF9F00),
                modifier = Modifier.clickable {
                    navController.navigate("SignUpComponent")
                }
            )
        }


    }
}