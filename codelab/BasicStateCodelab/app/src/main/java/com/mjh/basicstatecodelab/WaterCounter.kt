package com.mjh.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
    상태를 끌어올릴 때 상태의 이동 위치를 쉽게 파악할 수 있는 세 가지 규칙이 있습니다.

    상태는 적어도 그 상태를 사용하는 모든 컴포저블의 가장 낮은 공통 상위 요소로 끌어올려야 합니다(읽기).
    상태는 최소한 변경될 수 있는 가장 높은 수준으로 끌어올려야 합니다(쓰기).

    두 상태가 동일한 이벤트에 대한 응답으로 변경되는 경우 두 상태는 동일한 수준으로 끌어올려야 합니다.
    이러한 규칙에서 요구하는 것보다 더 높은 수준으로 상태를 끌어올릴 수 있습니다.
    하지만 상태를 충분히 높은 수준으로 끌어올리지 않으면 단방향 데이터 흐름을 따르기가 어렵거나 불가능할 수 있습니다.
*/

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableIntStateOf(0) }
    StatelessCounter(count, { count++ }, modifier)
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

// State Hoisting 처리를 한 덕분에 Stateless 컴포저블을 재사용할 수 있습니다.
// StatefulCounter1의 경우는 매칭되는 count 값이 변경된 경우만 리컴포지션을 수행하고
// StatefulCounter2의 경우는 count 값이 변경되면 둘 모두 리컴포지션이 수행됩니다.
@Composable
fun StatefulCounter1() {
    var waterCount by remember { mutableIntStateOf(0) }
    var juiceCount by remember { mutableIntStateOf(0) }

    StatelessCounter(waterCount, { waterCount++ })
    StatelessCounter(juiceCount, { juiceCount++ })
}

@Composable
fun StatefulCounter2() {
    var count by remember { mutableIntStateOf(0) }

    StatelessCounter(count, { count++ })
    StatelessCounter(count, { count *= 2 })
}