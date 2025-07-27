package com.eunhye.jetchat.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.eunhye.jetchat.R
import com.eunhye.jetchat.theme.JetchatTheme

@Composable
fun JetchatIcon(contentDescription: String?, modifier: Modifier = Modifier) {
    // semantics는 접근성 정보를 뷰에 추가하기 위해 사용하는 Modifier
    val semantics = if(contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        modifier
    }
    Box(modifier = modifier.then(semantics)) {
        Icon(
            painter = painterResource(id = R.drawable.ic_jetchat_back),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primaryContainer,
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_jetchat_front),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview
@Composable
fun JetchatIconPreview() = JetchatTheme {
    JetchatIcon("")
}
