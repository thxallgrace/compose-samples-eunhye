package com.eunhye.jetchat.widget

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

// GlanceAppWidgetReceiver는 위젯 업데이트, 시스템 이벤트를 자동으로 처리
class WidgetReceiver : GlanceAppWidgetReceiver() {

    // glanceAppWidget 프로퍼티를 오버라이드해서 위젯 UI를 실제로 그려주는 인스턴스를 반환
    override val glanceAppWidget: GlanceAppWidget
        get() = JetChatWidget()
}
