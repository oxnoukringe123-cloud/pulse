package com.pulse.app.model

data class Server(
    val id: String,
    val name: String,
    val emoji: String,
    val accentColor: Long,
    val unread: Int = 0
)

data class Channel(
    val id: String,
    val name: String,
    val type: ChannelType = ChannelType.TEXT,
    val unread: Int = 0
)

enum class ChannelType { TEXT, VOICE }

data class Chat(
    val id: String,
    val name: String,
    val emoji: String,
    val lastMessage: String,
    val time: String,
    val unread: Int = 0,
    val muted: Boolean = false,
    val online: Boolean = false
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

    val serverChannels = listOf(
        Channel("c1", "общий"),
        Channel("c2", "флудилка", unread = 5),
        Channel("c3", "мемы"),
        Channel("c4", "новости", unread = 2),
        Channel("c5", "голосовой", ChannelType.VOICE),
        Channel("c6", "музыка", ChannelType.VOICE),
    )

    val personalChats = listOf(
        Chat("p1", "Аня", "👩", "Привет! Как дела?", "10:24", unread = 2, online = true),
        Chat("p2", "Команда Pulse", "💜", "Аня: погнали код ревью", "10:25", unread = 5),
        Chat("p3", "Борис", "🧑", "Скинь файл пожалуйста", "10:26", online = true),
        Chat("p4", "Мама", "👩‍🦰", "Позвони как сможешь", "10:30", unread = 1),
        Chat("p5", "Рабочий чат", "💼", "Вы: готово ✅", "11:02", muted = true),
        Chat("p6", "Sasha", "🧔", "ок, договорились", "вчера"),
        Chat("p7", "Yohji", "🦊", "Смотри что нашёл", "вчера", unread = 1),
        Chat("p8", "m1lny", "🐧", "https://github.com/...", "пн"),
        Chat("p9", "Mentolow", "🧙", "ОНО ЕЩЁ И НА АЙФОН ЕСТЬ", "пн"),
        Chat("p10", "Brain burger", "🍔", "Погнали в 8?", "вс"),
    )

    val messages = listOf(
        Message("m1", "Аня", "Привет всем! 👋", "10:24", avatarEmoji = "👩"),
        Message("m2", "Борис", "Привет-привет, что нового?", "10:25", avatarEmoji = "🧑"),
        Message("m3", "Ты", "Работаю над новым мессенджером 🚀", "10:26", isMine = true),
        Message("m4", "Аня", "О, круто! А можно посмотреть?", "10:27", avatarEmoji = "👩"),
        Message("m5", "Ты", "Да, скоро соберу APK 😎", "10:28", isMine = true),
        Message("m6", "Борис", "Ждём демо 🔥", "10:29", avatarEmoji = "🧑"),
        Message("m7", "Ты", "Ок, вечером залью сборку", "10:30", isMine = true),
    )
}
