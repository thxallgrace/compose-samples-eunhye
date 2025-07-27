package com.eunhye.jetchat.conversation

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.toMutableStateList
import com.eunhye.jetchat.R

class ConversationUiState(
    val channelName: String,
    val channelMembers: Int,
    initialMessages: List<Message>
) {
    private val _messages: MutableList<Message> = initialMessages.toMutableStateList()
    val messages : List<Message> = _messages

    fun addMessage(message: Message) {
        _messages.add(0, message) // Add to the beginning of the list
    }
}

@Immutable
data class Message(
    val author: String,
    val content: String,
    val timestamp: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") R.drawable.ali else R.drawable.someone_else,
)
