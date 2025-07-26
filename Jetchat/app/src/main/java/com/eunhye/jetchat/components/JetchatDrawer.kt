package com.eunhye.jetchat.components

import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import com.eunhye.jetchat.theme.JetchatTheme

@Composable
fun JetchatDrawer(
    content: @Composable () -> Unit
) {

    // 컴포넌트별로 코드만 복사해서 붙여도 최소한 Theme 적용이 되게끔 theme를 컴포넌트 내부에 래핑
    JetchatTheme {
        ModalNavigationDrawer(
            drawerContent = {},
            content = content
        )
    }
}
