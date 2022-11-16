package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 11/10/22.
 *
 */

@Composable
fun <T> SelectableBottomSheetView(
    modifier: Modifier = Modifier,
    barLabel: String,
    optionList: List<T>,
    title: (T) -> String,
    icon: ((T) -> ImageVector)? = null,
    drawableResource: ((T) -> Int)? = null,
    searchHint: String = stringResource(id = R.string.search),
    onFiltered: ((String) -> List<T>)? = null,
    onSearchReset: (() -> Unit)? = null,
    onClick: (T) -> Unit,
) {
    val labelToShow = barLabel.replace("*", "")
    val searchKey = remember { mutableStateOf("") }
    val listShowing: List<T> = if (searchKey.value.isEmpty()) optionList
    else onFiltered!!(searchKey.value)
    Box(
        modifier = modifier
            .wrapContentHeight()
            .background(color = MaterialTheme.colors.background)
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier.padding(vertical = twelveDp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Engineering,
                    contentDescription = labelToShow,
                    modifier = modifier.padding(eightDp),
                    tint = MaterialTheme.colors.primary,
                )

                Text(
                    text = barLabel,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Divider(
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.primary
            )

            LazyColumn(
                modifier = Modifier.padding(sixteenDp),
                verticalArrangement = Arrangement.spacedBy(eightDp)
            ) {

                if (onFiltered != null) {
                    item {
                        SearchView(
                            modifier = Modifier.fillMaxWidth(),
                            state = searchKey,
                            label = searchHint,
                            style = MaterialTheme.typography.caption,
                            onTextChanged = { onFiltered.invoke(it) },
                            onReset = onSearchReset
                        )
                    }
                }

                items(listShowing) {
                    Column(
                        modifier = modifier
                            .clickable { onClick(it) }
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                    {
                        Row(
                            modifier = Modifier.padding(vertical = eightDp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            icon?.invoke(it)?.let {
                                Icon(
                                    imageVector = it,
                                    contentDescription = barLabel,
                                    modifier = modifier.padding(eightDp),
                                    tint = MaterialTheme.colors.primary,
                                )
                            }

                            drawableResource?.invoke(it)?.let {
                                Image(
                                    painter = painterResource(id = it),
                                    contentDescription = barLabel,
                                    modifier = modifier.padding(eightDp).size(twentyDp),
                                )
                            }

                            Text(
                                modifier = Modifier,
                                text = title(it)
                            )
                        }
                    }
                }

            }
        }
    }
}
