package com.eunhye.jetchat.widget.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.text.Text
import com.eunhye.jetchat.NavActivity
import com.eunhye.jetchat.R
import com.eunhye.jetchat.conversation.Message
import com.eunhye.jetchat.widget.theme.JetChatGlanceTextStyles
import com.eunhye.jetchat.widget.theme.JetchatGlanceColorScheme

// Glance 위젯은 안드로이드 위젯 시스템에서 작동하기 떄문에 Compose UI에서 자유롭게 쓰던
// Context 직접 접근, 리소스 사용이 제한점
// ImageProvider : Glance에서 제공하는 이미지 리소스 로딩 방법
@Composable
fun MessagesWidget(
    title: String,
    messages: List<Message>
) {
    Scaffold(titleBar = {
        TitleBar(
            startIcon = ImageProvider(R.drawable.ic_jetchat),
            iconColor = null,
            title = title,
        )
    }, backgroundColor = JetchatGlanceColorScheme.colors.background) {
        LazyColumn(modifier = GlanceModifier.fillMaxWidth()) {
            messages.forEach {
                item {
                    Column(modifier = GlanceModifier.fillMaxWidth()) {
                        MessageItem(it)
                        Spacer(modifier = GlanceModifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    Column(modifier = GlanceModifier.clickable(actionStartActivity<NavActivity>()).fillMaxWidth()) {
        Text(
            text = message.author,
            style = JetChatGlanceTextStyles.titleMedium,
        )
        Text(
            text = message.content,
            style = JetChatGlanceTextStyles.bodyMedium,
        )
    }
}

@Preview
@Composable
fun MessageItemPreview() {
    MessageItem(Message("John", "This is a preview of the message Item", "8:02PM"))
}

@Preview
@Composable
fun WidgetPreview() {
    MessagesWidget(
        "",
        listOf(Message("John", "This is a preview of the message Item", "8:02PM"))
    )
}
