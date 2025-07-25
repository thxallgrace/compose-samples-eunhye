package com.eunhye.jetchat.data

import com.eunhye.jetchat.R
import com.eunhye.jetchat.conversation.ConversationUiState
import com.eunhye.jetchat.conversation.Message
import com.eunhye.jetchat.data.EMOJIS.EMOJI_CLOUDS
import com.eunhye.jetchat.data.EMOJIS.EMOJI_FLAMINGO
import com.eunhye.jetchat.data.EMOJIS.EMOJI_MELTING
import com.eunhye.jetchat.data.EMOJIS.EMOJI_PINK_HEART
import com.eunhye.jetchat.data.EMOJIS.EMOJI_POINTS
import com.eunhye.jetchat.profile.ProfileScreenState

val initialMessages = listOf(
    Message(
        "me",
        "Check it out!",
        "8:07 PM",
    ),
    Message(
        "me",
        "Thank you!$EMOJI_PINK_HEART",
        "8:06 PM",
        R.drawable.sticker,
    ),
    Message(
        "Taylor Brooks",
        "You can use all the same stuff",
        "8:05 PM",
    ),
    Message(
        "Taylor Brooks",
        "@aliconors Take a look at the `Flow.collectAsStateWithLifecycle()` APIs",
        "8:05 PM",
    ),
    Message(
        "John Glenn",
        "Compose newbie as well $EMOJI_FLAMINGO, have you looked at the JetNews sample? " +
                "Most blog posts end up out of date pretty fast but this sample is always up to " +
                "date and deals with async data loading (it's faked but the same idea " +
                "applies) $EMOJI_POINTS https://goo.gle/jetnews",
        "8:04 PM",
    ),
    Message(
        "me",
        "Compose newbie: I’ve scourged the internet for tutorials about async data " +
                "loading but haven’t found any good ones $EMOJI_MELTING $EMOJI_CLOUDS. " +
                "What’s the recommended way to load async data and emit composable widgets?",
        "8:03 PM",
    ),
    Message(
        "Shangeeth Sivan",
        "Does anyone know about Glance Widgets its the new way to build widgets in Android!",
        "8:08 PM",
    ),
    Message(
        "Taylor Brooks",
        "Wow! I never knew about Glance Widgets when was this added to the android ecosystem",
        "8:10 PM",
    ),
    Message(
        "John Glenn",
        "Yeah its seems to be pretty new!",
        "8:12 PM",
    ),
)

val unreadMessages = initialMessages.filter { it.author != "me" }

val exampleUiState = ConversationUiState(
    initialMessages = initialMessages,
    channelName = "#composers",
    channelMembers = 42,
)

/**
 * Example colleague profile
 */
val colleagueProfile = ProfileScreenState(
    userId = "12345",
    photo = R.drawable.someone_else,
    name = "Taylor Brooks",
    status = "Away",
    displayName = "taylor",
    position = "Senior Android Dev at Openlane",
    twitter = "twitter.com/taylorbrookscodes",
    timeZone = "12:25 AM local time (Eastern Daylight Time)",
    commonChannels = "2",
)

/**
 * Example "me" profile.
 */
val meProfile = ProfileScreenState(
    userId = "me",
    photo = R.drawable.ali,
    name = "Ali Conors",
    status = "Online",
    displayName = "aliconors",
    position = "Senior Android Dev at Yearin\nGoogle Developer Expert",
    twitter = "twitter.com/aliconors",
    timeZone = "In your timezone",
    commonChannels = null,
)

object EMOJIS {
    // EMOJI 15
    const val EMOJI_PINK_HEART = "\uD83E\uDE77"

    // EMOJI 14 🫠
    const val EMOJI_MELTING = "\uD83E\uDEE0"

    // ANDROID 13.1 😶‍🌫️
    const val EMOJI_CLOUDS = "\uD83D\uDE36\u200D\uD83C\uDF2B️"

    // ANDROID 12.0 🦩
    const val EMOJI_FLAMINGO = "\uD83E\uDDA9"

    // ANDROID 12.0  👉
    const val EMOJI_POINTS = " \uD83D\uDC49"
}
