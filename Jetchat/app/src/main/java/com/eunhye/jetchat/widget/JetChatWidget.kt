package com.eunhye.jetchat.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import com.eunhye.jetchat.R
import com.eunhye.jetchat.data.unreadMessages
import com.eunhye.jetchat.widget.composables.MessagesWidget

class JetChatWidget : GlanceAppWidget() {

    // 위젯 콘텐츠를 제공
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // 내부에서 provideContent를 통해 Glance UI를 그리게 됨

        // Glance 내부에서는 리소스 string을 직접 Context를 불러서 쓰기보다는, GlanceAppWidget의 context.getString() 메서드를 suspend 함수 안에서 직접 호출하는 것이 권장
        val title = context.getString(R.string.messages_widget_title)

        provideContent {
            GlanceTheme {
                MessagesWidget(
                    title,
                    unreadMessages.toList()
                )
            }
        }
    }
}
