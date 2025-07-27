package com.eunhye.jetchat.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import com.eunhye.jetchat.theme.JetchatTheme

@Composable
fun JetchatDrawer(
    drawerState: DrawerState,
    selectedMenu: String,
    onProfileClicked: (String) -> Unit,
    onChatClicked: (String) -> Unit,
    content: @Composable () -> Unit
) {

    // 컴포넌트별로 코드만 복사해서 붙여도 최소한 Theme 적용이 되게끔 theme를 컴포넌트 내부에 래핑
    JetchatTheme {
        ModalNavigationDrawer( // 탐색 창 구현
            drawerState = drawerState, // 창의 상태
            drawerContent = { // 창의 콘텐츠 정
                ModalDrawerSheet( // 창에 Material Design 스타일 제공
                    drawerState = drawerState,
                    drawerContainerColor = MaterialTheme.colorScheme.background,
                    drawerContentColor = MaterialTheme.colorScheme.onBackground,
                ) {
                    JetchatDrawerContent(
                        onProfileClicked = onProfileClicked,
                        onChatClicked = onChatClicked,
                        selectedMenu = selectedMenu,
                    )
                }
            },
            content = content
        )
    }
}
