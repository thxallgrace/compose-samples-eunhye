package com.eunhye.jetchat.components

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eunhye.jetchat.R
import com.eunhye.jetchat.data.colleagueProfile
import com.eunhye.jetchat.data.meProfile
import com.eunhye.jetchat.theme.JetchatTheme
import com.eunhye.jetchat.widget.WidgetReceiver

@Composable
fun JetchatDrawerContent(
    onProfileClicked: (String) -> Unit, onChatClicked: (String) -> Unit, selectedMenu: String = "composers"
) {
    Column {
        // Spacer높이를 시스템 바 높이와 같이 지정
        // 화면을 edge-to-edge(전체화면)로 디자인할 때 앱의 내용이 Statue Bar에 가려지지 않도록 하기 위함
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        DrawerHeader()
        DividerItem()
        DrawerItemHeader("Chats")
        ChatItem("composers", selectedMenu == "composers") {
            onChatClicked("composers")
        }
        ChatItem("droidcon-nyc", selectedMenu == "droidcon-nyc") {
            onChatClicked("droidcon-nyc")
        }
        DividerItem(modifier = Modifier.padding(horizontal = 28.dp))
        DrawerItemHeader("Recent Profiles")
        ProfileItem(
            "Ali Conors (you)", meProfile.photo,
            selectedMenu == meProfile.userId,
        ) {
            onProfileClicked(meProfile.userId)
        }
        ProfileItem(
            "Taylor Brooks", colleagueProfile.photo,
            selectedMenu == colleagueProfile.userId,
        ) {
            onProfileClicked(colleagueProfile.userId)
        }
        if (widgetAddingIsSupported(LocalContext.current)) {
            DividerItem(modifier = Modifier.padding(horizontal = 28.dp))
            DrawerItemHeader("Settings")
            WidgetDiscoverability()
        }
    }
}

@Composable
fun DrawerHeader() {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = CenterVertically) {
        JetchatIcon(
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
        Image(
            painter = painterResource(id = R.drawable.jetchat_logo),
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun ChatItem(text: String, selected: Boolean, onChatClicked: () -> Unit) {
    val background = if(selected) {
        Modifier.background(MaterialTheme.colorScheme.primaryContainer)
    } else {
        Modifier
    }
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .then(background)
            .clickable(onClick = onChatClicked),
        verticalAlignment = CenterVertically,
    ) {
        val iconTint = if(selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_jetchat),
            tint = iconTint,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            contentDescription = null
        )
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}

@Composable
fun ProfileItem(text: String, @DrawableRes profilePic : Int?, selected: Boolean = false, onProfileClicked: () -> Unit) {
    val background = if (selected) {
        Modifier.background(MaterialTheme.colorScheme.primaryContainer)
    } else {
        Modifier
    }
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .then(background)
            .clickable(onClick = onProfileClicked),
        verticalAlignment = CenterVertically,
    ) {
        val paddingSizeModifier = Modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            .size(24.dp)
        if (profilePic != null) {
            Image(
                painter = painterResource(id = profilePic),
                modifier = paddingSizeModifier.then(Modifier.clip(CircleShape)),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            Spacer(modifier = paddingSizeModifier)
        }
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}

@Composable
fun DrawerItemHeader(text: String) {
    Box(
        modifier = Modifier
            .heightIn(min = 52.dp)
            .padding(horizontal = 28.dp),
        contentAlignment = CenterStart,
    ) {
        Text(
            text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
fun DividerItem(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    )
}

@Composable
@Preview
fun DrawerPreview() = JetchatTheme {
    Surface {
        Column {
            JetchatDrawerContent({}, {})
        }
    }
}

@Composable
@Preview
fun DrawerPreviewDark() = JetchatTheme(isDarkTheme = true) {
    Surface {
        Column {
            JetchatDrawerContent({}, {})
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WidgetDiscoverability() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = {
                addWidgetToHomeScreen(context)
            }),
        verticalAlignment = CenterVertically,
    ) {
        Text(
            stringResource(id = R.string.add_widget_to_home_page),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}

// API 26(오레오) 이상에서만 이 함수가 호출될 수 있음을 컴파일러에 명시
@RequiresApi(Build.VERSION_CODES.O)
private fun addWidgetToHomeScreen(context: Context) {
    // 위젯 관리자 인스턴스
    val appWidgetManager = AppWidgetManager.getInstance(context)

    // 위젯의 리시버 클래스를 지정
    val myProvider = ComponentName(context, WidgetReceiver::class.java)
    if (widgetAddingIsSupported(context)) {
        // 홈 스크린에 위젯 추가 요청
        appWidgetManager.requestPinAppWidget(myProvider, null, null)
    }
}

// 내부적으로 SDK 버전 체크를 수행하는 것 의미
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
private fun widgetAddingIsSupported(context: Context): Boolean {
    // 해당 기기가 requestPinAppWidget 가 지원되는지 체크
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            AppWidgetManager.getInstance(context).isRequestPinAppWidgetSupported
}
