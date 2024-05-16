package com.breens.orderfood.feature_tasks.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.breens.orderfood.feature_tasks.state.TasksScreenUiState
import com.breens.orderfood.theme.Cam
import com.breens.orderfood.theme.Xam

@Composable
fun CardComponent(
    uiState: TasksScreenUiState,
    navController: NavHostController,
) {
    LazyRow(
        contentPadding = PaddingValues(start = 12.dp,top=20.dp, bottom = 20.dp),
        modifier = Modifier.background(color = Color.White)
    ) {
        items(count = uiState.tasks.size) { index ->
            Box(modifier = Modifier
                .padding(end = 12.dp)
                .clickable { }
                .border(width = 0.dp, color = Xam)
                .background(color = Xam)
            ) {
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Image(
                        painter = rememberImagePainter(uiState.tasks[index].image),
                        contentDescription = "Movie Image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(width = 160.dp, height = 170.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = uiState.tasks[index].title,
                        style = MaterialTheme.typography.subtitle1,

                        modifier = Modifier
                            .size(width = 160.dp, height = 40.dp)
                            .padding(start = 7.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Box(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .border(width = 1.dp, color = Cam, shape = RoundedCornerShape(4.dp))
                            .clip(RoundedCornerShape(4.dp))
                    ) {
                        Text(
                            text = "Mã giảm 15%",
                            fontSize = 12.sp,
                            color = Cam,
                            modifier = Modifier.padding(3.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
@Preview(showBackground = true)
fun DemoCardComponent() {
Surface {
    CardComponent(uiState = TasksScreenUiState(), navController = NavHostController(LocalContext.current))

}
}