package com.pulse.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.pulse.app.model.ChannelType
import com.pulse.app.model.MockData

@Composable
fun MainScreen() {
    var personalMode by remember { mutableStateOf(false) }
    var selectedServerId by remember { mutableStateOf(MockData.servers.first().id) }
    var selectedChannelId by remember { mutableStateOf(MockData.serverChannels.first().id) }
    var chatTitle by remember { mutableStateOf("общий") }
    var isChannel by remember { mutableStateOf(true) }

    val selectedServer = MockData.servers.first { it.id == selectedServerId }

    Row(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        ServerRail(
            selectedId = selectedServerId,
            personalMode = personalMode,
            onSelectServer = {
                personalMode = false
                selectedServerId = it.id
                isChannel = true
                chatTitle = "общий"
                selectedChannelId = MockData.serverChannels.first().id
            },
            onOpenPersonal = {
                personalMode = true
                isChannel = false
                chatTitle = "Аня"
            },
        )

        ChatListPanel(
            title = if (personalMode) "Сообщения" else selectedServer.name,
            channels = MockData.serverChannels,
            personalChats = MockData.personalChats,
            showChannels = !personalMode,
            selectedChannelId = selectedChannelId,
            onChannelClick = {
                selectedChannelId = it.id
                chatTitle = it.name
                isChannel = it.type == ChannelType.TEXT
            },
            onChatClick = {
                chatTitle = it.name
                isChannel = false
            }
        )

        ChatView(
            title = chatTitle,
            isChannel = isChannel,
            messages = MockData.messages
        )
    }
}
