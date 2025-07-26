package com.eunhye.jetchat.conversation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.eunhye.jetchat.theme.JetchatTheme

@Composable
fun ConversationContent(

) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Messages(listOf(
                Message("me","hello1","",null),
                Message("eunye","hi","",null),
                Message("song","nice to meet you","",null)
            ))
            UserInput()
        }
    }
}

@Composable
fun Messages(
    messages: List<Message>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn {
            for(index in messages.indices) {
                item {
                    Message(messages[index])
                }
            }
        }
    }
}

@Composable
fun Message(message: Message) {
    Text(text = "${message.author} / ${message.content}")
}

@Preview
@Composable
fun ConversationContentPreview() = JetchatTheme {
    ConversationContent()
}
