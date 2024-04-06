package com.loryblu.feature.logbook.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBCardSoftBlue
import com.loryblu.core.ui.theme.LBSoftBlue
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.home.R

@Composable
fun TaskCardComponent(
    modifier: Modifier = Modifier,
    taskItem: LogbookTask,
    parentAccess: Boolean,
    onEdit: () -> Unit,
) {
    ElevatedCard(
        modifier
            .height(208.dp)
            .width(312.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TitleAndDragButton(
                categoryText = stringResource(id = taskItem.itemOfCategory.category.text),
                parentAccess = parentAccess
            )
            TaskContent(modifier = Modifier.height(126.dp), taskItem, parentAccess, onEdit)
            TaskName(
                modifier = Modifier.height(46.dp),
                taskName = stringResource(id = taskItem.itemOfCategory.text)
            )
        }
    }
}

@Composable
fun TitleAndDragButton(modifier: Modifier = Modifier, categoryText: String, parentAccess: Boolean) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(LBSoftBlue),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = categoryText, textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        if(parentAccess) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    )
                ) {
                    Image(painter = painterResource(id = R.drawable.drag_icon), contentDescription = "")
                }
            }
        }
    }
}

@Composable
fun TaskContent(
    modifier: Modifier = Modifier,
    taskItem: LogbookTask,
    parentAccess: Boolean,
    onEdit: () -> Unit
) {
    Row(
        modifier
            .background(taskItem.shift.color)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter =
            painterResource(id = taskItem.itemOfCategory.drawable),
            contentDescription = "",
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(107.dp)
                .width(140.dp)
                .weight(0.5f),
            contentScale = ContentScale.FillHeight
        )
        if (parentAccess) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 12.dp)
                    .weight(0.5f),
                horizontalAlignment = Alignment.End,
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onEdit,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LBSoftBlue,
                        contentColor = Color.Black
                    ),
                    shape = ShapeDefaults.Small,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Edit, contentDescription = "as")
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Editar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LBSoftBlue,
                        contentColor = Color.Black
                    ),
                    shape = ShapeDefaults.Small,
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = "as")
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Deletar",
                    )
                }
            }
        }
    }
}

@Composable
fun TaskName(modifier: Modifier = Modifier, taskName: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(LBSoftBlue),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = taskName, textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

//@Preview
//@Composable
//private fun TaskCardPreview() {
//    TaskCardComponent()
//}