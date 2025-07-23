package com.eunhye.jetchat.conversation

import androidx.compose.runtime.Immutable
import com.eunhye.jetchat.R

class ConversationUiState(
    val channelName: String,
    val channelMembers: Int,
    initialMessages: List<Message>
) {
}

@Immutable
data class Message(
    val author: String,
    val content: String,
    val timestamp: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") R.drawable.ali else R.drawable.someone_else,
)
