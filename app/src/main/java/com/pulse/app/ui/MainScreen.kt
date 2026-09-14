package com.pulse.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.pulse.app.model.MockData

@Composable
fun MainScreen() {
    var selectedServerId by remember { mutableStateOf(MockData.servers.first().id) }
    var selectedChatId by remember { mutableStateOf<String?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }

    val selectedServer = MockData.servers.first { it.id == selectedServerId }
    val chats = MockData.chats
    val selectedChat = chats.firstOrNull { it.id == selectedChatId }

    Row(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        ServerRail(
            selectedId = selectedServerId,
            personalMode = false,
            onSelectServer = {
                selectedServerId = it.id
            },
            onOpenPersonal = { /* пока отключено */ },
        )

        ChatListPanel(
            title = selectedServer.name,
            chats = chats,
            selectedChatId = selectedChatId,
            onChatClick = { selectedChatId = it.id },
            onAddChat = { showAddDialog = true }
        )

        ChatView(
            chatId = selectedChatId,
            chatName = selectedChat?.name ?: "Чат"
        )
    }

    if (showAddDialog) {
        AddChatDialog(
            onDismiss = { showAddDialog = false },
            onCreate = { name ->
                val chat = MockData.createChat(name)
                selectedChatId = chat.id
                showAddDialog = false
            }
        )
    }
}
