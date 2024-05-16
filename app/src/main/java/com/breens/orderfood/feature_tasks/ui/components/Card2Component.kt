package com.breens.orderfood.feature_tasks.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.breens.orderfood.R
import com.breens.orderfood.data.model.Card
import com.breens.orderfood.feature_tasks.state.CardsScreenUiState
import com.breens.orderfood.feature_tasks.state.TasksScreenUiState
import com.breens.orderfood.theme.Xam

@Composable
fun Card2Component(
    uiStateCard: CardsScreenUiState,
    navController: NavHostController,
    setCardImage:(String)->Unit,
    setCardTitle: (String) -> Unit,
    setCardBody: (String) -> Unit,
    setCardPrice: (Int) -> Unit,
    setCardFavorite: (Int) -> Unit,
    setCardSales: (Int) -> Unit,
    updateFavorite: (Card) -> Unit,
    saveFavorite: () -> Unit,
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = "Đề xuất cho bạn",
                style = MaterialTheme.typography.h6,
            )
            TextButton(onClick = { }) {
                Text(text = "Xem tất cả")
            }
        }
        LazyRow(
            contentPadding = PaddingValues(start = 12.dp,top=20.dp, bottom = 20.dp),
            modifier = Modifier.background(color = Color.White)
        ) {
            items(count = uiStateCard.cards.size) { index ->
                setCardTitle(uiStateCard.cards[index].titleCard)
                  setCardImage(uiStateCard.cards[index].imageCard)
                  setCardBody(uiStateCard.cards[index].bodyCard)
                  setCardPrice(uiStateCard.cards[index].priceCard)
                  setCardSales(uiStateCard.cards[index].sale)
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
                            painter = rememberImagePainter(uiStateCard.cards[index].imageCard),
                            contentDescription = "Movie Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.size(width = 160.dp, height = 170.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = uiStateCard.cards[index].titleCard,
                            style = MaterialTheme.typography.subtitle1,

                            modifier = Modifier
                                .size(width = 160.dp, height = 40.dp)
                                .padding(start = 7.dp)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(start = 3.dp)
                        ) {
                            Row( verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_star_24),
                                    contentDescription = "star",
                                    tint = Color.Yellow
                                )
                                Text(text = "4.8 (900 views)")
                            }
                            Spacer(modifier = Modifier.width(10.dp))

                                Icon(
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = "heart",
                                    modifier = Modifier
                                        .clickable {
                                            updateFavorite(uiStateCard.cards[index])
                                            setCardFavorite(1)
                                            saveFavorite()
                                        }
                                )

                        }
                        Spacer(modifier = Modifier.height(10.dp))

                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}

