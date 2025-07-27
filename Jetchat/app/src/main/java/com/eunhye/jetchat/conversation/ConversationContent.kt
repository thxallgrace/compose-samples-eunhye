package com.eunhye.jetchat.conversation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.eunhye.jetchat.R
import com.eunhye.jetchat.components.JetchatAppBar
import com.eunhye.jetchat.theme.JetchatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationContent(
    uiState: ConversationUiState,
    onNavIconPressed: () -> Unit = { },
) {
    // LazyRow, LazyColumn같은 리스트의 스크롤 상태를 기억하는 객체
    // 얼마나 스크롤되었는지, 어떤 아이템이 보이는지 등의 정보를 갖고 있음
    val scrollState = rememberLazyListState()
    val topBarState = rememberTopAppBarState()
    // 고정된 스크롤 동작을 만드는 함수
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)

    Scaffold(
        topBar = {
            ChannelNameBar(
                channelName = uiState.channelName,
                channelMembers = uiState.channelMembers,
                scrollBehavior = scrollBehavior,
                onNavIconPressed = onNavIconPressed
            )
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime)
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Messages(List(100) {
                Message("me", "hello1", "", null)
            })
            UserInput()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelNameBar(
    channelName: String,
    channelMembers: Int,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = { },
) {
    JetchatAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        onNavIconPressed = onNavIconPressed,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Channel name
                Text(
                    text = channelName,
                    style = MaterialTheme.typography.titleMedium,
                )
                // Number of members
                Text(
                    text = stringResource(R.string.members, channelMembers),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
        actions = { }
    )
}

@Composable
fun Messages(
    messages: List<Message>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn {
            for (index in messages.indices) {
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
    ConversationContent(
        uiState = ConversationUiState(
            channelName = "", channelMembers = 10, initialMessages = listOf(),
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChannelBarPrev() {
    JetchatTheme {
        ChannelNameBar(channelName = "composers", channelMembers = 52)
    }
}
