package com.mjh.composeanimationbox.animation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mjh.composeanimationbox.AnimationScreen
import com.mjh.composeanimationbox.R

@Composable
fun ParallaxAnimationScreen(navController: NavController) {
    AnimationScreen(
        navController = navController,
        title = "Parallax Animation",
        animationContent = { isExecuted ->
            if (isExecuted) {
                ParallaxEffectContent()
            } else {
                Text("Start the animation")
            }
        }
    )
}

@Composable
fun ParallaxEffectContent() {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            // 패럴랙스 배경 이미지
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp) // 원하는 높이 설정
                    .graphicsLayer {
                        translationY = (scrollState.firstVisibleItemScrollOffset / 2).toFloat()
                    }
            )
        }

        item {
            // 소개 섹션
            Section(
                title = "소개",
                content = "패럴랙스 애니메이션 화면에 오신 것을 환영합니다! 여기서는 배경 이미지가 전경 콘텐츠보다 느리게 움직이는 패럴랙스 스크롤링 효과를 시연합니다."
            )
        }

        item {
            // 기능 섹션
            Section(
                title = "기능",
                content = "• 부드러운 패럴랙스 효과\n• 여러 섹션이 있는 스크롤 리스트\n• 동적 콘텐츠 렌더링"
            )
        }

        item {
            // 사용 방법 섹션
            Section(
                title = "사용 방법",
                content = "리스트를 스크롤하여 패럴랙스 효과를 확인하세요. 배경 이미지는 전경 콘텐츠보다 느리게 움직여 시각적으로 매력적인 경험을 제공합니다."
            )
        }

        item {
            // 기타 정보 섹션
            Section(
                title = "기타 정보",
                content = "• 최신 안드로이드 기기용으로 설계됨\n• Jetpack Compose 기반\n• 사용자 정의 가능 및 확장 가능"
            )
        }

        item {
            // 업데이트 섹션
            Section(
                title = "최근 업데이트",
                content = "• 새로운 패럴랙스 효과 추가\n• 애니메이션 성능 향상\n• 사용자 피드백을 반영한 버그 수정"
            )
        }
    }
}

@Composable
fun Section(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


