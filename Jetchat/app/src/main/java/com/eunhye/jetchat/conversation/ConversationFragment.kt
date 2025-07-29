package com.eunhye.jetchat.conversation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.eunhye.jetchat.MainViewModel
import com.eunhye.jetchat.data.exampleUiState
import com.eunhye.jetchat.theme.JetchatTheme

class ConversationFragment : Fragment() {
    private val activityViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(inflater.context).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            setContent {
                JetchatTheme {
                    ConversationContent(
                        uiState = exampleUiState,

                        onNavIconPressed = { activityViewModel.openDrawer() }
                    )
                }
            }
        }
}
