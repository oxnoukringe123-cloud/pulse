package com.pulse.app.model

import androidx.compose.runtime.mutableStateListOf

data class Server(
    val id: String,
    val name: String,
    val emoji: String,
    val accentColor: Long,
    val unread: Int = 0
)

data class Chat(
    val id: String,
    val name: String,
    val emoji: String = "💬"
)

data class Message(
    val id: String,
    val author: String,
    val text: String,
    val time: String,
    val isMine: Boolean = false,
    val avatarEmoji: String = "👤"
)

object MockData {
    val servers = listOf(
        Server("s1", "Друзья", "🏠", 0xFF5865F2, unread = 3),
        Server("s2", "Работа", "💼", 0xFF23A55A),
        Server("s3", "Игры", "🎮", 0xFFED4245, unread = 12),
        Server("s4", "Учёба", "📚", 0xFFFEE75C),
        Server("s5", "Музыка", "🎵", 0xFFEB459E, unread = 1),
    )

    // Общий список чатов — пустой, заполняется пользователем
    val chats = mutableStateListOf<Chat>()

    // Сообщения по каждому чату. Ключ — id чата
    val messagesByChat = mutableMapOf<String, MutableList<Message>>()

    fun getMessages(chatId: String): MutableList<Message> {
        return messagesByChat.getOrPut(chatId) { mutableListOf() }
    }

    fun createChat(name: String): Chat {
        val chat = Chat(
            id = "chat_${System.currentTimeMillis()}",
            name = name.ifBlank { "Новый чат" },
            emoji = "💬"
        )
        chats.add(chat)
        messagesByChat[chat.id] = mutableListOf()
        return chat
    }
}
