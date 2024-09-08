package com.mjh.reorderablelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReorderableScreen() {
    var list by remember { mutableStateOf((0 until 20).toList()) }
    var scrollToIndex by remember { mutableStateOf<Int?>(null) }
    val draggableItems by remember { derivedStateOf { list.size } }
    val stateList = rememberLazyListState()

    val dragDropState = rememberDragDropState(
        lazyListState = stateList,
        draggableItemsNum = draggableItems,
        onMove = { fromIndex, toIndex ->
            val adjustedFromIndex = list.size - 1 - fromIndex
            val adjustedToIndex = list.size - 1 - toIndex
            list = list.toMutableList().apply {
                add(adjustedToIndex, removeAt(adjustedFromIndex))
            }
        }
    )

    LaunchedEffect(scrollToIndex) {
        scrollToIndex?.let { index ->
            stateList.animateScrollToItem(index)
            scrollToIndex = null
        }
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                val newIndex = list.size
                list = list.toMutableList().apply { add(newIndex) }
                scrollToIndex = 0
            }) {
                Text("Add Item")
            }
        }

        LazyColumn(
            modifier = Modifier
                .dragContainer(dragDropState)
                .fillMaxSize(),
            state = stateList,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(text = "Memo", fontSize = 30.sp)
            }

            draggableItems(items = list.reversed(), dragDropState = dragDropState) { modifier, item ->
                Item(
                    modifier = modifier,
                    index = item,
                    onRemove = { itemToRemove ->
                        list = list.toMutableList().filterNot { it == itemToRemove }
                    }
                )
            }
        }
    }
}


@Composable
private fun Item(modifier: Modifier = Modifier, index: Int, onRemove: (Int) -> Unit) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Item $index",
                fontSize = 18.sp
            )
            Box(
                modifier = Modifier
                    .clickable(
                        onClick = { onRemove(index) },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Remove item"
                )
            }
        }
    }
}