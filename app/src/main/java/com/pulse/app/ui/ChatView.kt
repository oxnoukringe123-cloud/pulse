package com.pulse.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pulse.app.model.MockData
import com.pulse.app.model.Message
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ChatView(
    chatId: String?,
    chatName: String,
) {
    var input by remember { mutableStateOf("") }
    val messages = remember(chatId) {
        if (chatId != null) MockData.getMessages(chatId) else mutableListOf()
    }
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        // Шапка
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(DarkPanel)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = chatName,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        if (chatId == null) {
            // Ничего не выбрано
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Выбери чат слева",
                    color = TextSecondary,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            // Сообщения
            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1f).fillMaxWidth().padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                if (messages.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Пока нет сообщений. Напиши первое!",
                                color = TextSecondary,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                items(messages) { msg -> MessageBubble(msg) }
            }

            // Поле ввода
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(DarkHover, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = input,
                    onValueChange = { input = it },
                    placeholder = { Text("Сообщение...", color = TextSecondary) },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                    )
                )
                IconButton(
                    onClick = {
                        val text = input.trim()
                        if (text.isNotEmpty()) {
                            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                            messages.add(
                                Message(
                                    id = "m${System.currentTimeMillis()}",
                                    author = "Ты",
                                    text = text,
                                    time = time,
                                    isMine = true
                                )
                            )
                            input = ""
                        }
                    }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Отправить", tint = PulseAccent)
                }
            }
        }
    }
}

@Composable
fun MessageBubble(msg: Message) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (msg.isMine) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (msg.isMine) PulseAccent else DarkPanel,
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                if (!msg.isMine) {
                    Text(
                        msg.author,
                        color = PulseAccent,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                    Spacer(Modifier.height(2.dp))
                }
                Text(
                    msg.text,
                    color = if (msg.isMine) Color.White else TextPrimary,
                    fontSize = 15.sp
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    msg.time,
                    color = if (msg.isMine) Color.White.copy(alpha = 0.7f) else TextSecondary,
                    fontSize = 11.sp
                )
            }
        }
    }
}
