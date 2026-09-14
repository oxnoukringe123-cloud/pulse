package com.pulse.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pulse.app.model.Channel
import com.pulse.app.model.ChannelType
import com.pulse.app.model.Chat

@Composable
fun ChatListPanel(
    title: String,
    channels: List<Channel>,
    personalChats: List<Chat>,
    showChannels: Boolean,
    selectedChannelId: String,
    onChannelClick: (Channel) -> Unit,
    onChatClick: (Chat) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(DarkPanel)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.Search,
                contentDescription = "Поиск",
                tint = TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        }

        Box(Modifier.fillMaxWidth().height(1.dp).background(DarkDivider))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (showChannels) {
                item {
                    Text(
                        text = "ТЕКСТОВЫЕ КАНАЛЫ",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 14.dp, top = 12.dp, bottom = 6.dp)
                    )
                }
                items(channels.filter { it.type == ChannelType.TEXT }) { channel ->
                    ChannelRow(
                        channel = channel,
                        selected = channel.id == selectedChannelId,
                        onClick = { onChannelClick(channel) }
                    )
                }
                item {
                    Text(
                        text = "ГОЛОСОВЫЕ КАНАЛЫ",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 14.dp, top = 12.dp, bottom = 6.dp)
                    )
                }
                items(channels.filter { it.type == ChannelType.VOICE }) { channel ->
                    ChannelRow(
                        channel = channel,
                        selected = channel.id == selectedChannelId,
                        onClick = { onChannelClick(channel) }
                    )
                }
                item {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                            .height(1.dp)
                            .background(DarkDivider)
                    )
                    Text(
                        text = "ЛИЧНЫЕ СООБЩЕНИЯ",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 14.dp, top = 4.dp, bottom = 6.dp)
                    )
                }
            } else {
                item {
                    Text(
                        text = "ВСЕ ЧАТЫ",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 14.dp, top = 12.dp, bottom = 6.dp)
                    )
                }
            }

            items(personalChats) { chat ->
                ChatRow(chat = chat, onClick = { onChatClick(chat) })
            }
        }
    }
}

@Composable
private fun ChannelRow(channel: Channel, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(if (selected) DarkHover else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (channel.type == ChannelType.VOICE) Icons.Default.VolumeUp else Icons.Default.Tag,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = channel.name,
            color = if (selected) TextPrimary else TextSecondary,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        if (channel.unread > 0) {
            Surface(color = PulseAccent, shape = CircleShape, modifier = Modifier.size(18.dp)) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(channel.unread.toString(), color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun ChatRow(chat: Chat, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { onClick() }
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Surface(
                modifier = Modifier.size(46.dp).clip(CircleShape),
                color = DarkHover
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(chat.emoji, fontSize = 22.sp)
                }
            }
            if (chat.online) {
                Surface(
                    modifier = Modifier.align(Alignment.BottomEnd).size(12.dp),
                    color = PulseOnline,
                    shape = CircleShape
                ) {}
            }
        }
        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = chat.name,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
                if (chat.muted) {
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        Icons.Default.NotificationsOff,
                        contentDescription = "Muted",
                        tint = TextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
            Spacer(Modifier.height(2.dp))
            Text(
                text = chat.lastMessage,
                color = TextSecondary,
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(Modifier.width(6.dp))
        Column(horizontalAlignment = Alignment.End) {
            Text(chat.time, color = TextSecondary, fontSize = 11.sp)
            if (chat.unread > 0) {
                Spacer(Modifier.height(4.dp))
                Surface(
                    color = if (chat.muted) TextSecondary else PulseAccent,
                    shape = CircleShape,
                    modifier = Modifier.height(20.dp).widthIn(min = 20.dp)
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().padding(horizontal = 6.dp)) {
                        Text(
                            text = if (chat.unread > 99) "99+" else chat.unread.toString(),
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
