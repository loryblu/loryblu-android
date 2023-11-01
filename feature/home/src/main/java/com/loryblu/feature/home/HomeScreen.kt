package com.loryblu.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.loryblu.core.ui.R
import com.loryblu.core.ui.components.LBCard
import com.loryblu.logbook.model.GameTrackHome
import com.loryblu.logbook.model.LogbookHome
import com.loryblu.logbook.model.StoryTrackHome

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    val homeItems = arrayOf(
        LogbookHome(),
        StoryTrackHome(),
        GameTrackHome()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(zIndex = 1f)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 12.dp, start = 26.dp, bottom = 32.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_home),
                contentDescription = "loryblu logo menu",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(62.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                fontSize = 24.sp,
                text = "Olá, Bia" //this resource will come from api
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
            ) {
                item {
                    LBCard(
                        idImage = homeItems[0].idImage,
                        text = homeItems[0].imageText,
                        image = homeItems[0].imageDrawable,
                        modifier = Modifier
                            .weight(0.3f)
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(top = 12.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
                        onclick = {
                                  //should go to the next screen
                        },
                    )
                }
                item {
                    LBCard(
                        idImage = homeItems[1].idImage,
                        text = homeItems[1].imageText,
                        image = homeItems[1].imageDrawable,
                        modifier = Modifier
                            .weight(0.3f)
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(top = 12.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
                        onclick = {},
                        isCardDisabled = true
                    )
                }
                item {
                    LBCard(
                        idImage = homeItems[2].idImage,
                        text = homeItems[2].imageText,
                        image = homeItems[2].imageDrawable,
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(top = 12.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
                        onclick = {},
                        isCardDisabled = true
                    )
                }
            }
        }
    }
}
