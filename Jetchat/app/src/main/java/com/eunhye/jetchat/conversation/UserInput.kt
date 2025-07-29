package com.eunhye.jetchat.conversation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eunhye.jetchat.R

enum class InputSelector {
    NONE,
    MAP,
    DM,
    EMOJI,
    PHONE,
    PICTURE,
}

enum class EmojiStickerSelector {
    EMOJI,
    STICKER,
}


@Composable
fun UserInput(onMessageSent: (String) -> Unit, modifier: Modifier = Modifier, resetScroll: () -> Unit = {}) {
    var currentInputSelector by rememberSaveable { mutableStateOf(InputSelector.EMOJI) }

    // TextFieldValue : 문자열, 커서 위치, 선택 영역 등 편집상태를 가지고 있음
    // Saver 가 내부 저장/복원
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue()) // 빈 텍스트 상태
    }

    Surface(tonalElevation = 2.dp, contentColor = MaterialTheme.colorScheme.secondary) {
        Column(modifier = modifier) {
            UserInputText()
            UserInputSelector(
                onSelectorChange = { currentInputSelector = it},
                sendMessageEnabled = false,
                onMessageSent = {
                    onMessageSent(textState.text)
                    textState = TextFieldValue()
                    resetScroll()
                },
                currentInputSelector = currentInputSelector,
            )
            SelectorExpanded()
        }
    }
}

@Composable
fun UserInputText() {

}

@Composable
fun UserInputSelector(
    onSelectorChange: (InputSelector) -> Unit,
    sendMessageEnabled: Boolean,
    onMessageSent: () -> Unit,
    currentInputSelector: InputSelector,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(72.dp)
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InputSelectorButton(
            onClick = { onSelectorChange(InputSelector.EMOJI) },
            icon = painterResource(id = R.drawable.ic_mood),
            selected = currentInputSelector == InputSelector.EMOJI,
            description = stringResource(id = R.string.emoji_selector_bt_desc),
        )
        InputSelectorButton(
            onClick = { onSelectorChange(InputSelector.DM) },
            icon = painterResource(id = R.drawable.ic_alternate_email),
            selected = currentInputSelector == InputSelector.DM,
            description = stringResource(id = R.string.dm_desc),
        )
        InputSelectorButton(
            onClick = { onSelectorChange(InputSelector.PICTURE) },
            icon = painterResource(id = R.drawable.ic_insert_photo),
            selected = currentInputSelector == InputSelector.PICTURE,
            description = stringResource(id = R.string.attach_photo_desc),
        )
        InputSelectorButton(
            onClick = { onSelectorChange(InputSelector.MAP) },
            icon = painterResource(id = R.drawable.ic_place),
            selected = currentInputSelector == InputSelector.MAP,
            description = stringResource(id = R.string.map_selector_desc),
        )
        InputSelectorButton(
            onClick = { onSelectorChange(InputSelector.PHONE) },
            icon = painterResource(id = R.drawable.ic_duo),
            selected = currentInputSelector == InputSelector.PHONE,
            description = stringResource(id = R.string.videochat_desc),
        )
        val border = if(!sendMessageEnabled) {
            BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
        } else { null }
        Spacer(modifier = Modifier.weight(1f))

        val disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

        val buttonColor = ButtonDefaults.buttonColors(
            disabledContainerColor = Color.Transparent,
            disabledContentColor = disabledContentColor
        )

        Button(
            modifier = Modifier.height(36.dp),
            enabled = sendMessageEnabled,
            onClick = onMessageSent,
            colors = buttonColor,
            border = border,
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                stringResource(R.string.send),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun InputSelectorButton(
    onClick: () -> Unit,
    icon: Painter,
    description: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    val backgroundModifier = if(selected) {
        Modifier.background(
            color = LocalContentColor.current,
            shape = RoundedCornerShape(16.dp)
        )
    } else {
        Modifier
    }
    IconButton(
        onClick = onClick,
        modifier = modifier.then(backgroundModifier)
    ) {
        val tint = if(selected) {
            // 현재 콘텐츠 색상의 대비되는 색상
            contentColorFor(LocalContentColor.current)
        } else {
            // 현재 콘텐츠 색상
            LocalContentColor.current
        }
        Icon(
            painter = icon,
            tint = tint,
            modifier = Modifier.padding(8.dp).size(56.dp),
            contentDescription = description,
        )
    }
}

@Composable
fun SelectorExpanded() {}

@Preview
@Composable
fun UserInputPreview() {
    UserInput()
}
