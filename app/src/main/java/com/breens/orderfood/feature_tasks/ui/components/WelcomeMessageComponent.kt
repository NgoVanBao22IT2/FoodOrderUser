package com.breens.orderfood.feature_tasks.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.breens.orderfood.R
import com.breens.orderfood.feature_tasks.state.OrderScreenUiState

@Composable
fun WelcomeMessageComponent(navController: NavController) {

    Row(
        modifier = Modifier
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.duc),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .border(BorderStroke(2.dp, Color.Gray), shape = CircleShape)
                .clip(CircleShape)
                .size(60.dp)


        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Xin chào, Đức👋,",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

        }

    }
}

@Composable
@Preview(showBackground = true)
fun DemoWelcomeMessage() {
    Surface {
       WelcomeMessageComponent(navController = NavController(LocalContext.current))
    }
}
